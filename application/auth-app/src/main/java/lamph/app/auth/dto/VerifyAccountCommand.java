package lamph.app.auth.dto;

public record VerifyAccountCommand(String accountId, String verifyCode) {
}
