package com.embl.test.restfulmicroservice.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DocumentationConstants {
	
	/**
	   *  The Constant is description about the field name . 
	   */
  public static final String LOCALE = "Indicates the locale in which the API response and the error messages "
    + "will be responded. The locale should be mentioned in the "
    + "<a href=\"http://www.oracle.com/technetwork/java/javase/java8locales-2095355.html\">"
    + "Java standard locale format</a>.";
  
  public static final String RESPONSECODE_200 = "Success.";
  public static final String RESPONSECODE_201 = "Created.";
  public static final String RESPONSECODE_202 = "Accepted.";
  public static final String RESPONSECODE_204 = "No Content";
  public static final String RESPONSECODE_400 = "Bad Request.";
  public static final String RESPONSECODE_401 = "Unauthorized.";
  public static final String RESPONSECODE_403 = "Forbidden.";
  public static final String RESPONSECODE_404 = "The resource you are trying to reach is not found.";
  public static final String RESPONSECODE_422 = "Unprocessable Entity.";
  public static final String RESPONSECODE_409 = "Conflict Request";
  public static final String RESPONSECODE_500 = "Internal Server Error occured.";
  
  /** This variable holds the description for request parameter <b>page</b> */
  public static final String PAGE_DESC = "Specifies the page number to be retrieved where the size of the page "
    + "must be specified by the parameter size, offset, or pageSize. "
    + "The first page starts with the value \"1\".";

  /** This variable holds the description for request parameter <b>size/pageSize/offset</b> */
  public static final String PAGE_SIZE_DESC = "Indicates the number of documents being "
    + "retrieved on the corresponding page specified by the page parameter.";


}
