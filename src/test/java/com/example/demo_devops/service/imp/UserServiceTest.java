package com.example.demo_devops.service.imp;


import com.example.demo_devops.entity.User;
import com.example.demo_devops.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitaires de UserService")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1, "Ahmed", "ahmed@email.com");
    }

    @Test
    @DisplayName("Doit sauvegarder un utilisateur avec succès")
    void saveUser_Success() {
        // Arrange
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User result = userService.saveUser(user);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("Ahmed");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Doit retourner tous les utilisateurs")
    void getAllUsers_Success() {
        // Arrange
        List<User> users = Arrays.asList(
                new User(1, "Ahmed", "ahmed@email.com"),
                new User(2, "Fatma", "fatma@email.com")
        );
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getUsername()).isEqualTo("Ahmed");
    }

    @Test
    @DisplayName("Doit trouver un utilisateur par ID")
    void getUserById_Found() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.getUserById(1);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo("Ahmed");
    }

    @Test
    @DisplayName("Doit retourner vide si utilisateur non trouvé")
    void getUserById_NotFound() {
        // Arrange
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userService.getUserById(99);

        // Assert
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Doit mettre à jour un utilisateur existant")
    void updateUser_Success() {
        // Arrange
        User updatedUser = new User(1, "Ahmed Updated", "new@email.com");
        when(userRepository.existsById(1)).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        // Act
        User result = userService.updateUser(1, updatedUser);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("Ahmed Updated");
    }

    @Test
    @DisplayName("Doit retourner null si utilisateur à mettre à jour n'existe pas")
    void updateUser_NotFound() {
        // Arrange
        when(userRepository.existsById(99)).thenReturn(false);
        User result = userService.updateUser(99, user);
// Assert
        assertThat(result).isNull();
        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Doit supprimer un utilisateur")
    void deleteUser_Success() {
// Arrange
        doNothing().when(userRepository).deleteById(1);
// Act
        userService.deleteUser(1);
// Assert
        verify(userRepository, times(1)).deleteById(1);
    }
}