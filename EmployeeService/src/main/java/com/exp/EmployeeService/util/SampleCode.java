package com.exp.EmployeeService.util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

public class SampleCode {

   static Function<Flux<String>,Flux<String>> transformName =
                                    name->name.map(String::toUpperCase);

   static Function<String,Integer> parseToInteger = val->{
       try{
           return Integer.parseInt(val);
       }catch (Exception e){
           return null;
       }
   };
    static Flux<String> splitString(String name){
        return Flux.fromArray(name.split(""));
    }
    public static void main(String arg[]){
        //Flux example
        Flux<String> flux = Flux.fromIterable(List.of("Biswo","Bikash","Pabitra"));
        flux.subscribe(name->{
           // System.out.println(name);
        });

        //Mono example
        Mono<String> mono = Mono.just("Biswojit");
        mono.subscribe(name->{
               // System.out.println(name)
        });

        //map() example
        var flux1= flux.map(String::toUpperCase).
                //filter
                filter(name->name.charAt(0)=='B')
                //flatMap: It will return the result as B,I,S,W,O,B,I,K,A,S,H
                .flatMap(name->splitString(name)).log();

        //transform()
        flux.transform(transformName).subscribe(name->{});

        //defaultIfEmpty() : accepts a value
        var flux2 =flux.map(String::toUpperCase).
                        filter(name->name.length()>10).
                        defaultIfEmpty("Default value").log();

        //switchIfEmpty() : accepts a MONO or FLUX
       var flux3 = flux.map(String::toUpperCase).
                filter(name->name.length()>10).
                switchIfEmpty(Flux.just("Default")).log();

       //concat(): used to combine multiple flux and returns one
       Flux<String> result = Flux.concat(flux1,flux2);
       //concatWith(): used to combine two flux or mono and returns one flux
       flux1.concatWith(flux2);

       Flux.zip(flux,flux1).
       subscribe(name->System.out.println(name));

    }
}
