package com.test.primes.model;

import java.io.Serializable;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * PrimesResponse
 */
@XmlRootElement(name = "PrimeResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrimesResponse implements Serializable {
  @JsonProperty("initial")
  private Integer initial = null;

  @JsonProperty("primes")
  private Set<Integer> primes = null;

  public PrimesResponse initial(Integer initial) {
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

  public PrimesResponse primes(Set<Integer> primes) {
    this.primes = primes;
    return this;
  }

  public PrimesResponse addPrimesItem(Integer primesItem) {
    if (this.primes == null) {
      this.primes = new HashSet<Integer>();
    }
    this.primes.add(primesItem);
    return this;
  }

  /**
   * Get primes
   * @return primes
  **/
  public Set<Integer> getPrimes() {
    return primes;
  }

  public void setPrimes(Set<Integer> primes) {
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
    PrimesResponse primesResponse = (PrimesResponse) o;
    return Objects.equals(this.initial, primesResponse.initial) &&
        Objects.equals(this.primes, primesResponse.primes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(initial, primes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("PrimesResponse {\n");
    
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
