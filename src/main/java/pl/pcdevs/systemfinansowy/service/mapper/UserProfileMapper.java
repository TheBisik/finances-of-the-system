package pl.pcdevs.systemfinansowy.service.mapper;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pl.pcdevs.systemfinansowy.dto.UserProfileDto;

@Service
public class UserProfileMapper {
    public UserProfileDto toDto(OAuth2AuthenticationToken authentication) {
        OAuth2User user = authentication.getPrincipal(); //pobieram z oauth2 springa aktualnie zalogowanego użtykownika
        String sub = user.getAttribute("sub"); // pobieram id sub z niego
        String name  = user.getAttribute("name");      // lub "given_name"/"family_name"
        String picture   = user.getAttribute("picture");
        return new UserProfileDto(sub, name, picture);
    }
}
