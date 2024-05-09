package kr.easw.lesson07;


/**
 * 해당 클래스는 익명 클래스 및 제너릭스에 대해 조금 더 친숙해지기 위해 구성된 문제입니다.
 *
 * 주석을 읽어 코드를 이해하고, 코드가 정상적으로 동작하도록 수정해보세요.
 * 해당 코드는 의도적으로 오류가 발생하도록 구성되었습니다.
 */
public class GenericsExample {

    public static void main(String[] args) {
        Recipe<Ingredient, Cake> recipe = new Recipe<>(new Flour()) {
            @Override
            public Cake cook() {
                // 제너릭스를 통해 클래스를 확정했음으로, T 제너릭스가 Ingredient를 상속받는것이 보장됩니다.
                // 그러므로, 원래라면 호출할 수 없는 Ingredient의 mix() 메서드를 호출할 수 있게 됩니다.
                getIngredient().mix();
                return new Cake();
            }
        };
        // 반환값 또한 같습니다. 제너릭스를 통해 클래스를 확정했음으로, R 제너릭스가 Cake이거나 Cake 클래스를 상속받은 클래스임이 보장됩니다.
        recipe.cook().bake();
    }

    // 제너릭스는 클래스나 메서드를 정의할 때, 타입을 파라미터로 사용할 수 있게끔 하는 기능입니다.
    // 예를 들어, 이 클래스에서는 T를 재료 클래스로, R을 반환 클래스로 사용하고 있습니다. 다만, 이 클래스에서는 제너릭스를 확정하지 않았음으로 클래스를 생성하기 전까지는 T와 R이 무엇인지 알 수 없습니다.
    // 위의 main 메서드를 보면, Recipe 옆의 꺾쇠 안에 2개의 클래스 이름이 적혀있습니다. 이것이 제너릭스의 확정입니다.
    // 이를 통해, Recipe 클래스는 Ingredient 클래스를 재료로, Cake 클래스를 반환값으로 사용하게 됩니다.
    abstract static class Recipe<T, R> {
        // 제너릭스를 사용하여 재료를 저장할 수 있게끔 합니다.
        private final T ingredient;

        public Recipe(T ingredient) {
            this.ingredient = ingredient;
        }

        public T getIngredient() {
            return ingredient;
        }

        // 제너릭스에 지정된 R 클래스를 반환값으로 사용합니다.
        public abstract R cook();
    }

    // 제너릭스는 자바의 다형성을 구축할때 중요한 역할을 수행합니다.
    // 제너릭스 자체로는 런타임상에서 타입을 알 수 없지만, 컴파일 타임에 타입을 알 수 있게끔 해줍니다.
    // 이로써, 파라미터 및 반환 타입에 동적으로 타입을 강제할 수 있기에 코드의 안정성을 높일 수 있습니다.
    interface Ingredient {
        void mix();
    }

    static class Flour implements Ingredient {
        @Override
        public void mix() {
            System.out.println("Mixing flour...");
        }
    }

    static class RottenEgg implements Ingredient {
        @Override
        public void mix() {
            throw new RuntimeException("Rotten egg detected!");
        }
    }

    static class Egg implements Ingredient {
        @Override
        public void mix() {
            System.out.println("Mixing egg...");
        }
    }

    static class Cake {
        public void bake() {
            System.out.println("Done!");
        }
    }



}
