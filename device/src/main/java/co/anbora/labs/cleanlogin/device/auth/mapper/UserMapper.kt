package co.anbora.labs.cleanlogin.device.auth.mapper

import co.anbora.labs.cleanlogin.domain.model.User
import com.google.firebase.auth.FirebaseUser
import java.util.function.Function

class UserMapper : Function<FirebaseUser, User> {

    override fun apply(firebaseUser: FirebaseUser): User {

        return User.Builder()
                .id(firebaseUser.uid)
                .name(firebaseUser.displayName)
                .build()
    }
}