package com.ykeshtdar.demoP6.repository;

import com.ykeshtdar.demoP6.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Integer> {
}
