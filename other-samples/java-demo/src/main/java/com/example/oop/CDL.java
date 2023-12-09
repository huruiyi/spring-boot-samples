package com.example.oop;

import org.junit.jupiter.api.Test;

public class CDL {


    interface Container {
        Object getDependency(String key);
    }

    class DefaultContainer implements Container {
        @Override
        public Object getDependency(String key) {
            if ("myDependency".equals(key)) {
                return new Dependency();
            }
            throw new RuntimeException("Unknown dependency: " + key);
        }
    }

    class Dependency {
        @Override
        public String toString() {
            return "Hello from " + getClass();
        }
    }

    interface ManagedComponent {
        void performLookup(Container container);
    }

    class ContextualizedDependencyLookup implements ManagedComponent {
        private Dependency dependency;

        @Override
        public void performLookup(Container container) {
            this.dependency = (Dependency) container.getDependency("myDependency");
        }

        @Override
        public String toString() {
            return dependency.toString();
        }
    }


    @Test
    public void cdl() {
        Container container = new DefaultContainer();

        ManagedComponent managedComponent = new ContextualizedDependencyLookup();
        managedComponent.performLookup(container);

        System.out.println(managedComponent);
    }

}
