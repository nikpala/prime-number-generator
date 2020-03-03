package com.test.primes.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Response
 */
@Validated
public class Response   {
  @JsonProperty("initial")
  private Integer initial = null;

  @JsonProperty("primes")
  @Valid
  private List<Integer> primes = null;

  public Response initial(Integer initial) {
    this.initial = initial;
    return this;
  }

  /**
   * Get initial
   * @return initial
  **/
  public Integer getInitial() {
    return initial;
  }

  public void setInitial(Integer initial) {
    this.initial = initial;
  }

  public Response primes(List<Integer> primes) {
    this.primes = primes;
    return this;
  }

  public Response addPrimesItem(Integer primesItem) {
    if (this.primes == null) {
      this.primes = new ArrayList<Integer>();
    }
    this.primes.add(primesItem);
    return this;
  }

  /**
   * Get primes
   * @return primes
  **/
  public List<Integer> getPrimes() {
    return primes;
  }

  public void setPrimes(List<Integer> primes) {
    this.primes = primes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Response response = (Response) o;
    return Objects.equals(this.initial, response.initial) &&
        Objects.equals(this.primes, response.primes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(initial, primes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Response {\n");
    
    sb.append("    initial: ").append(toIndentedString(initial)).append("\n");
    sb.append("    primes: ").append(toIndentedString(primes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
