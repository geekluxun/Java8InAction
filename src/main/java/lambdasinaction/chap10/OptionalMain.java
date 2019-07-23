package lambdasinaction.chap10;

import java.util.*;

import static java.util.stream.Collectors.toSet;

public class OptionalMain {
    
    public static void main(String[] argc){
        OptionalMain optionalMain = new OptionalMain();
        Optional<Person> person = Optional.of(new Person());
        person.get().setCar(Optional.of(null));
        Optional<Car> car = person.get().getCar();
        car.get().setInsurance(Optional.of(new Insurance()));
        System.out.println("保险公司名：" + optionalMain.getCarInsuranceName(person));
    }

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                     .flatMap(Car::getInsurance)
                     .map(Insurance::getName)
                     .orElse("Unknown");
    }

//    public Set<String> getCarInsuranceNames(List<Person> persons) {
//        return persons.stream()
//                      .map(Person::getCar)
//                      .map(optCar -> optCar.flatMap(Car::getInsurance))
//                      .map(optInsurance -> optInsurance.map(Insurance::getName))
//                      .flatMap(Optional::stream)
//                      .collect(toSet());
//    }
}
