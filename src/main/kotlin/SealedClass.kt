
//// CLASE SELLADA
sealed class CanWalk(val legs: Int) {
    class Elephant(val name: String): CanWalk(4)
    class Spider(val age: Int): CanWalk(8)
}

//// INTERFACE SELLADA
sealed interface CanFly

//// OBJETOS
data object Swan: CanWalk(2), CanFly
data object Fly: CanFly

fun test(canWalk: CanWalk): Int {
    return when (canWalk) {
        is CanWalk.Elephant -> canWalk.name.toInt()
        is CanWalk.Spider -> canWalk.age
        is Swan -> TODO()
    }
}

fun test2(canFly: CanFly): Int = when(canFly) {
    Fly -> TODO()
    Swan -> TODO()
}


//// Las SEALED INTERFACE nos va a permitir que una clase extienda de diferentes interficies
//// Si necesitamos que esas clases hereden tambien parametros necesitaremos una SEALED CLASS