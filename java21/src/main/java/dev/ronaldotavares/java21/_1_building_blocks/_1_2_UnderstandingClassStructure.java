package dev.ronaldotavares.java21._1_building_blocks;

public class _1_2_UnderstandingClassStructure {
   public int numberVisitors(int month) {
      return 10;
   }

   /**
    * Javadoc multiple-line comment
    * @author Jeanne and Scott
    */
   private void comments(){
      // comment until end of line

      /* Multiple
       * line comment
       */

      /*
       * // anteater
       */

      // bear

      // // cat

      // /* dog */

      /* elephant */
      /*
       * /* ferret */
//      */ ADDITIONAL MULTILINE END COMMENT DOES NOT COMPILE
   }
}

class Animal {
   String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
class Animal2 {}