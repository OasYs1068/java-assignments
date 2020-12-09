package exercise1;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/***
**** Step #2: declare that the Family class is serializable.
***/
public class Person implements Serializable{
  // Attributes
  private String firstName;
  private String lastName;
  /***
  **** Step #3: declare that the numOfSiblings attribute is not
  **** to be serialized.
  ***/
  private transient int numOfSiblings;
  // Associations
  private Family family = null;

  // Constructor
  public Person(Family fam, String f, String l) {
    firstName = f;
    lastName = l;
    family = fam;
    if ( family != null ) {
      family.addChild(this);
    }
  }

  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public int getNumberOfSiblings() {
    /***
    **** Step #4: calculate the numOfSiblings attribute
    **** This should only be done once per execution.
    ***/
    if(numOfSiblings==0){
      numOfSiblings = family.getChildren().size()-1;
      return numOfSiblings;
    }
    else {
      return numOfSiblings;
    }
  }
}
