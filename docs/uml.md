@startuml
left to right direction

actor Usuario as User
actor Administrador as Admin
actor "Manager Restaurante" as Restaurant

package DondeKomemos {
    usecase "Crear perfil" as CreateAccount
    usecase "Editar perfil" as EditAccount
    usecase "Borrar perfil" as DeleteAccount
    usecase "Consultar restaurantes" as QueryRestaurant
    usecase "Crear reseña" as SubmitReview
    usecase "Borrar reseña" as DeleteReview
    usecase "Editar reseña" as EditReview
    usecase "Consultar reseñas" as QueryReviews
    usecase "Alta restaurante" as SubmitRestaurant
    usecase "Editar restaurante" as EditRestaurant
    usecase "Baja restaurante" as DeleteRestaurant
}

User --> DeleteAccount
User --> CreateAccount
User --> EditAccount
User --> QueryRestaurant
User --> SubmitReview
User --> DeleteReview
User --> EditReview
User --> QueryReviews
Admin --> DeleteAccount
Admin --> DeleteReview
Admin --> QueryReviews
Restaurant --> SubmitRestaurant
Restaurant --> EditRestaurant
Restaurant --> DeleteRestaurant
Admin --> DeleteRestaurant

@enduml

@startuml
class User {
    -name: String
    +User(String)
    +getName(): String
    +toString(): String
}

class Review {
    -rating: Integer
    -title: String
    -detail: String
    -user: User
    +Review(Integer, String, String, User)
    +getRating(): Integer
    +getTitle(): String
    +getDetail(): String
    +getUser(): User
    +toString(): String
}

class Restaurant {
    -reviews: ArrayList<Review>
    -name: String
    -address: String
    +Restaurant(String, String)
    +getReviews(): ArrayList<Review>
    +getName(): String
    +getAddress(): String
    +toString():String
}

class UserInterface {
    -br: BufferedReader
    +printRestaurant(Restaurant): void
    +getUserLine(): String
    +getUserRating(): Integer
    +Menu(): MenuOptions
    +addReview(Restaurant): void
    +newRestaurant(): Restaurant
    +pickRestaurant(Map<String, Restaurant>): String
}

enum MenuOptions {
    PRINT_ALL
    ADD_REVIEW
    EXIT
}

class App {
    -ui: UserInterface
    -restaurants: Map<String, Restaurant>
    +{static} main(String[]): void
}

App "0..n" *-- Restaurant
App *-- UserInterface
Restaurant "0..n" *-- Review
UserInterface -- MenuOptions
Review *-- User
@enduml
