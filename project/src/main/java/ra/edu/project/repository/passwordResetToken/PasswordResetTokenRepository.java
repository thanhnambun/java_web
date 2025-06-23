package ra.edu.project.repository.passwordResetToken;

public interface PasswordResetTokenRepository  {
    PasswordResetTokenRepository findByToken(String token);
}

