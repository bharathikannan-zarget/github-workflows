package com.freshworks.githubflows;

public class StringUtil {

  public boolean isEmpty(String s) {
    if (s == null || s.isEmpty()) return true;
    else return false;
  }

  public boolean isValid(String s) {
	if(s != null && s.length() > 0)
		return true;
	else
		return false;
  }
}
