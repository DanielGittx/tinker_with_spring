package com.gittx.demo.entities;

public enum Stat {
    PENDING,
    PROCESSING,
    REJECTED,
    APPROVED

}


/*


class Person {
 @NotNull
private String name;
  @NotNull(message = "email.empty")
   @Email(regexp = EMAIL_PATTERN, message = "email.invalid")
private String email;
 @NotNull(message = "phonenumber.empty")
   @Pattern(regexp = PHONE_NUMBER_PATTERN, message = "phonenumber.invalid")
private String phoneNumber;
...
}
 */