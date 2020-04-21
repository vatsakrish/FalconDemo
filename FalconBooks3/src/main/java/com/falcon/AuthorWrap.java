package com.falcon;

public class AuthorWrap extends Author{
	
	String profit;
 
	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	@Override
    public String toString() {
        return "Author{" +
                "id=" + super.getId() +
                ", name='" + super.getAuthname() + '\'' +
                ", location='" + super.getLocation() + '\'' +
                ", age=" + super.getAge() +
                '}';
    }
}
