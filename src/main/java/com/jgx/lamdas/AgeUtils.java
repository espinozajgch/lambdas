package com.jgx.lamdas;

class AgeUtils{
    @FunctionalInterface
    interface TriFunction<T, U, V, R>{
        R apply(T t,U u,V v);

    }

    @FunctionalInterface
    interface ZeroArgumentos{
        int get();
    }

    @FunctionalInterface
    interface nada{
        void nada();
    }

    @FunctionalInterface
    interface StringOperation{
        int getAmount();

        default void operate(String text){
            int x = getAmount();
            while(x-- >0){
                System.out.println(text);
            }
        }
    }
}
