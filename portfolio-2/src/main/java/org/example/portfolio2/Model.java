package org.example.portfolio2;
import java.util.*;

class Model {
  public List<String> baseProgram() {
    return Arrays.asList("NatBach","HumTek");
  }

  List<String> subjectModule() {
    return Arrays.asList("Computer Science","Informatik","Astrology");
  }

  List<String> baseCourse(String base) {
    return switch (base) {
      case "NatBach" -> Arrays.asList(
              "BK1 Empirical Data",
              "BK2 Experimental Methods",
              "BK3 Theory of Natural Science",
              "Logic and Discrete Mathematics",
              "Functional Biology – Zoology",
              "Linear Algebra",
              "Organic Chemistry",
              "Biological Chemistry",
              "Statistical Models",
              "Functional Programming and Language Implementations",
              "Classical Mechanics",
              "Environmental Science",
              "Cell Biology",
              "Functional biology – Botany",
              "Supplementary Physics",
              "Calculus",
              "The Chemical Reaction",
              "Scientific Computing",
              "Energy and Climate Changes"
      );
      case "HumTek" -> Arrays.asList(
              "Design og Konstruktion I+Workshop" ,
              "Subjektivitet, Teknologi og Samfund I" ,
              "Teknologiske systemer og artefakter I" ,
              "Videnskabsteori" ,
              "Design og Konstruktion II+Workshop" ,
              "Subjektivitet, Teknologi og Samfund II" ,
              "Bæredygtige teknologier" ,
              "Kunstig intelligens" ,
              "Medier og teknologi - datavisualisering" ,
              "Teknologiske Systemer og Artefakter II - Sundhedsteknologi" ,
              "Den (in)humane storby" ,
              "Interactive Design in the Field" ,
              "Organisation og ledelse af designprocesser"
      );
      default -> null;
    };
  }

  List<String> baseProject(String base) {
    return Arrays.asList("BP1 " + base, "BP2 " + base, "BP3 " + base, "Bachelorproject " + base);
  }

  List<String> subjectCourse(String base) {
    return switch (base) {
      case "Computer Science" -> Arrays.asList("Essential Computing",
              "Software Development", "Interactive Digital Systems");
      case "Informatik" -> Arrays.asList("Organisatorisk forandring og IT",
              "BANDIT", "Web baserede IT-Systemer");
      case "Astrology" -> Arrays.asList("Essential Astrology",
              "Venus studies", "Mars studies", "Ascendant calculations");
      default -> null;
    };
  }
  String subjectProject(String subject) {
    return "Subject module project in "+subject;
  }
  int courseWeight(String course){
    if (isProject(course)) {
      return 15;
    }
    return switch (course) {
      case "Software Development" -> 10;
      case "BANDIT" -> 10;
      default -> 5;
    };
  }
  boolean isProject(String s){
    for(String fm: subjectModule()){
      if (s.equals(subjectProject(fm))) {
        return true;
      }
    }
    for(String bs: baseProgram()) {
      if (baseProject(bs).contains(s)) {
        return true;
      }
    }
    return false;
  }
}
