import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/***
**** Step #1: declare that the Family class is serializable.
***/
public class Family {
  // Associations
  private Person mother = null;
  private Person father = null;
  private List   children = new ArrayList(5);

  public Family() {
  }

  public void setMother(Person p) {
    mother = p;
  }
  public void setFather(Person p) {
    father = p;
  }
  public void addChild(Person c) {
    children.add(c);
  }
  public List getChildren() {
    return children;
  }

  public Person findChild(String first_name) {
    Iterator  elements = children.iterator();
    Person    result = null;

    while ( elements.hasNext() && (result == null) ) {
      Person child = (Person) elements.next();

      if ( child.getFirstName().equals(first_name) ) {
	result = child;
      }
    }

    return result;
  }
}
