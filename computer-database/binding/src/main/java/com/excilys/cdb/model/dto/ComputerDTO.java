package com.excilys.cdb.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

import com.excilys.cdb.validator.Date;

public class ComputerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Min(value = 0, message = "Computer id size is not valid.")
    private long id;

    @NotNull
    @Size(min = 1, max = 255, message = "Computer name size is not valid.")
    private String name;

    @Min(value = 0, message = "Company id size is not valid.")
    private long companyId;

    @Value("")
    private String companyName;

    @Date(message = "Introduced date is not valid.")
    private String introduced;

    @Date(message = "Discontinued date is not valid.")
    private String discontinued;

    /**
     * Constructor.
     */
    public ComputerDTO() {
        name = "";
        companyName = "";
        introduced = "";
        discontinued = "";
    }

    @Override
    public int hashCode() {
        int hash = 6;

        hash = hash * 11 + Long.hashCode(id);
        hash = hash * 11 + name.hashCode();
        hash = hash * 11 + Long.hashCode(companyId);
        hash = hash * 11 + companyName.hashCode();
        hash = hash * 11 + introduced.hashCode();
        hash = hash * 11 + discontinued.hashCode();

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof ComputerDTO) {
            ComputerDTO computerDto = (ComputerDTO) obj;

            return this.id == computerDto.id && this.name.equals(computerDto.name)
                    && this.companyId == computerDto.companyId
                    && this.companyName.equals(computerDto.companyName)
                    && this.introduced.equals(computerDto.introduced)
                    && this.discontinued.equals(computerDto.discontinued);
        }

        return false;
    }

    @Override
    public String toString() {
        String res;

        res = "Computer (" + id + ") " + name;
        if (companyName != null) {
            res = res + " [" + companyName + "]";
        }

        if (introduced != null && !introduced.trim().isEmpty() && !introduced.equals("-")) {
            res = res + " from " + introduced;
        }

        if (discontinued != null && !discontinued.trim().isEmpty() && !discontinued.equals("-")) {
            res = res + " to " + discontinued;
        }

        return res;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIntroduced() {
        return introduced;
    }

    /**
     * Sets the date of introduction.
     *
     * @param introduced date of introduction - '-' if equals "null"
     */
    public void setIntroduced(String introduced) {
        if (introduced.equals("null")) {
            this.introduced = "-";
        } else {
            this.introduced = introduced;
        }
    }

    public String getDiscontinued() {
        return discontinued;
    }

    /**
     * Sets the date of discontinue.
     *
     * @param discontinued date of discontinue - '-' if equals "null"
     */
    public void setDiscontinued(String discontinued) {
        if (discontinued.equals("null")) {
            this.discontinued = "-";
        } else {
            this.discontinued = discontinued;
        }
    }
}
