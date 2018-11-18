package co.anbora.labs.cleanlogin.domain.auth

enum class AuthEnum(val authValue: Int) {

    GOOGLE(1),
    FACEBOOK(2),
    TWITTER(3),
    GITHUB(4),
    ANONYMOUS(5),
    PHONE(6),
    EMAIL(7),
    GOOGLE_PLAY(8)

}