package com.github.sentinel.pay.infrastructure.in.web.controller.demo;

import com.github.sentinel.pay.application.dto.apiKey.ApiKeyDto;
import com.github.sentinel.pay.application.dto.user.SettingInfoDto;
import com.github.sentinel.pay.application.dto.user.UserDto;
import com.github.sentinel.pay.application.services.*;
import com.github.sentinel.pay.application.usecases.CreateApiKeyUseCase;
import com.github.sentinel.pay.application.usecases.DeleteApiKeyUseCase;
import com.github.sentinel.pay.application.usecases.ListApiKeyByUserIdUseCase;
import com.github.sentinel.pay.application.usecases.LoadClienAccountIdDataUseCase;
import com.github.sentinel.pay.application.usecases.UpdatePasswordUseCase;
import com.github.sentinel.pay.application.usecases.UpdateUserUseCase;
import com.github.sentinel.pay.domain.entity.auth.apiKey.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SettingsController {
    private final UpdateUserUseCase updateUserUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final DeleteApiKeyUseCase deleteApiKeyUseCase;
    private final CreateApiKeyUseCase createApiKeyUseCase;
    private final ListApiKeyByUserIdUseCase listApiKeyUseCase;
    private final LoadClienAccountIdDataUseCase loadClienAccountIdDataUseCase;
    /**
     * GET /settings - Página principal de settings
     */
    @GetMapping("/settings")
    public String settingsPage(Model model) {
        System.out.println("SETTINGS RENDERED");
        // Obtener el usuario actual
        SettingInfoDto settingInfoDto= loadClienAccountIdDataUseCase.execute();
        System.out.println("settings data: "+settingInfoDto);
        // Agregar datos al modelo
        model.addAttribute("user", settingInfoDto.userDto);
        return "settings";
    }
    @GetMapping("/settings/api-keys")
    public String apiKeys(Model model) {
        // Your existing API keys loading        SettingInfoDto settingInfoDto= loadClienAccountIdDataUseCase.execute();

        SettingInfoDto settingInfoDto= loadClienAccountIdDataUseCase.execute();
        model.addAttribute("apiKeys", settingInfoDto.apiKeyDtoList);
        return "api-keys";
    }
    /**
     * POST /settings/profile - Actualizar perfil del usuario
     * HTMX retorna un fragmento con mensaje de éxito/error
     */
    @PostMapping("/settings/profile")
    public String updateProfile(
             @ModelAttribute("user") UserDto user,
            Model model) {
        try {
             updateUserUseCase.execute(user);
            // Mensaje de éxito para HTMX
            model.addAttribute("message", "Profile updated successfully!");
            model.addAttribute("messageType", "success");

        } catch (Exception e) {
            model.addAttribute("message", "Error updating profile: " + e.getMessage());
            model.addAttribute("messageType", "danger");
        }

        // Retornar fragmento con el mensaje
        return "fragments/alert :: alert";
    }

    /**
 * POST /settings/password - Actualizar contraseña
 */
@PostMapping("/settings/password")
@ResponseBody
public String updatePassword(
        @RequestParam String currentPassword,
        @RequestParam String newPassword,
        @RequestParam String confirmPassword) {
    try {
        // Validar que las contraseñas coincidan
        if (!newPassword.equals(confirmPassword)) {
            return """
                <div class="alert alert-danger border-danger mb-0" role="alert">
                    <div class="d-flex align-items-center">
                        <i class="bi bi-exclamation-triangle-fill me-2 fs-5"></i>
                        <div>
                            <strong>Error!</strong> Passwords do not match.
                        </div>
                    </div>
                </div>
                """;
        }
        
        // Llamar al use case
        updatePasswordUseCase.execute(currentPassword, newPassword);
        
        return """
            <div class="alert alert-success border-success mb-0" role="alert">
                <div class="d-flex align-items-center">
                    <i class="bi bi-check-circle-fill me-2 fs-5"></i>
                    <div>
                        <strong>Success!</strong> Password updated successfully.
                    </div>
                </div>
            </div>
            """;
            
    } catch (Exception e) {
        return """
            <div class="alert alert-danger border-danger mb-0" role="alert">
                <div class="d-flex align-items-center">
                    <i class="bi bi-exclamation-triangle-fill me-2 fs-5"></i>
                    <div>
                        <strong>Error!</strong> Failed to update password: %s
                    </div>
                </div>
            </div>
            """.formatted(e.getMessage());
    }
}
    /**
     * POST /settings/api-keys - Crear nueva API Key
     * HTMX retorna el fragmento HTML de la nueva key
     */
    @PostMapping("/settings/api-keys")
    public String createApiKey(
            @RequestParam String keyName,
            Model model) {

     ApiKeyDto apiKeyDto = createApiKeyUseCase.execute(keyName);

        // Agregar al modelo para renderizar
        model.addAttribute("key", apiKeyDto);

        // Retornar fragmento de la nueva key
        return "fragments/api-key-item :: api-key-item";
    }

    /**
     * DELETE /settings/api-keys/{id} - Eliminar API Key
     * HTMX retorna vacío (swap outerHTML eliminará el elemento)
     */
    @DeleteMapping("/settings/api-keys/{id}")
    @ResponseBody
    public String deleteApiKey(
            @PathVariable("id") UUID id) {
           deleteApiKeyUseCase.execute(id);
        // Retornar vacío para que HTMX elimine el elemento
        return "";
    }
}
