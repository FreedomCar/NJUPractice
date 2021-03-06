package freedomcar.njupractice.bl.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import trapx00.tagx00.blservice.account.UserBlService;
import trapx00.tagx00.dataservice.account.UserDataService;
import trapx00.tagx00.entity.account.TempUser;
import trapx00.tagx00.entity.account.User;
import trapx00.tagx00.exception.viewexception.*;
import trapx00.tagx00.response.user.UserLoginResponse;
import trapx00.tagx00.response.user.UserRegisterConfirmationResponse;
import trapx00.tagx00.response.user.UserRegisterResponse;
import trapx00.tagx00.security.jwt.JwtRole;
import trapx00.tagx00.security.jwt.JwtService;
import trapx00.tagx00.security.jwt.JwtUser;
import trapx00.tagx00.util.Converter;
import trapx00.tagx00.vo.user.UserSaveVo;

import java.util.Collection;

@Service
public class UserBlServiceImpl implements UserBlService {

    private final static long EXPIRATION = 604800;

    private final UserDataService userDataService;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Autowired
    public UserBlServiceImpl(UserDataService userDataService, @Qualifier("jwtUserDetailsServiceImpl") UserDetailsService userDetailsService, JwtService jwtService) {
        this.userDataService = userDataService;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    /**
     * user sign up
     *
     * @param userSaveVo the user info to be saved
     * @return the register info to response
     * @throws UserAlreadyExistsException the user already exists
     * @throws SystemException            the system has error
     */
    @Override
    public UserRegisterResponse signUp(UserSaveVo userSaveVo) throws UserAlreadyExistsException, SystemException, InvalidEmailAddressesException {
        if (userDataService.isUserExistent(userSaveVo.getUsername())) {
            throw new UserAlreadyExistsException();
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String code = generateSecurityCode();
            final String rawPassword = userSaveVo.getPassword();
            userSaveVo.setPassword(encoder.encode(rawPassword));

            TempUser tempUser = Converter.userSaveVoToTempUser(userSaveVo, code);
            JwtUser jwtUser = jwtService.convertTempUserToJwtUser(tempUser);
            userDataService.saveTempUser(tempUser);
            userDataService.sendEmail(code, userSaveVo.getEmail());
            String token = jwtService.generateToken(jwtUser, EXPIRATION);
            return new UserRegisterResponse(token);
        }
    }

    /**
     * confirm user's validation code
     *
     * @param token the user's token
     * @param code  the validation code
     * @return the register confirmation info to response
     */
    @Override
    public UserRegisterConfirmationResponse registerValidate(String token, String code) throws UserDoesNotExistException, WrongValidationCodeException, SystemException {
        String username = (String) jwtService.getClaimsFromToken(token).get("username");
        TempUser tempUser = userDataService.getTempUserByTempUsername(username);
        String realCode = tempUser.getValidationCode();
        if (realCode.equals(code)) {
            User user = Converter.tempUserToUser(tempUser);
            JwtUser jwtUser = jwtService.convertUserToJwtUser(user);
            userDataService.saveUser(user);
            userDataService.deleteTempUserByUsername(tempUser.getUsername());
            String loginToken = jwtService.generateToken(jwtUser, EXPIRATION);
            return new UserRegisterConfirmationResponse(loginToken, jwtUser.getAuthorities(), user.getEmail());
        } else {
            throw new WrongValidationCodeException();
        }
    }

    /**
     * login
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the login info to response
     * @throws WrongUsernameOrPasswordException the username or password is error
     */
    @Override
    public UserLoginResponse login(String username, String password) throws WrongUsernameOrPasswordException {
        if (userDataService.confirmPassword(username, password)) {
            JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);
            String token = jwtService.generateToken(jwtUser, EXPIRATION);
            String email = jwtUser.getEmail();
            Collection<JwtRole> jwtRoles = jwtUser.getAuthorities();
            return new UserLoginResponse(token, jwtRoles, email);
        } else {
            throw new WrongUsernameOrPasswordException();
        }
    }

    private String generateSecurityCode() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            result.append((int) Math.floor(Math.random() * 10));
        }
        return new String(result);
    }
}
