package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "PCESTDTT")
@IdClass(PcestdttId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pcestdtt implements Serializable {

    @Id
    @Column(name = "ESTIMATE_NO", length = 20, nullable = false)
    private String estimateNo;

    @Id
    @Column(name = "REV_NO", nullable = false)
    private BigDecimal revNo;

    @Id
    @Column(name = "DEPT_ID", length = 6, nullable = false)
    private String deptId;

    @Id
    @Column(name = "RES_CD", length = 20, nullable = false)
    private String resCd;

    @Column(name = "RES_TYPE", length = 20)
    private String resType;

    @Column(name = "RES_CAT")
    private BigDecimal resCat;

    @Column(name = "GEN_RES", length = 1)
    private String genRes;

    @Column(name = "UOM", length = 4)
    private String uom;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "ESTIMATE_QTY")
    private BigDecimal estimateQty;

    @Column(name = "ESTIMATE_COST")
    private BigDecimal estimateCost;

    @Column(name = "TOLERANCE")
    private BigDecimal tolerance;

    @Column(name = "ISSUED_QTY")
    private BigDecimal issuedQty;

    @Column(name = "COMMITED_QTY")
    private BigDecimal commitedQty;

    @Column(name = "COMMITED_COST")
    private BigDecimal commitedCost;

    @Column(name = "ISSUED_COST")
    private BigDecimal issuedCost;

    @Column(name = "NORM_DEFAULT", length = 1)
    private String normDefault;

    @Column(name = "REQUESTED_QTY")
    private BigDecimal requestedQty;

    @Column(name = "REQUESTED_COST")
    private BigDecimal requestedCost;

    @Column(name = "APPROVED_QTY")
    private BigDecimal approvedQty;

    @Column(name = "APPROVED_COST")
    private BigDecimal approvedCost;

    @Column(name = "RETURNED_QTY")
    private BigDecimal returnedQty;

    @Column(name = "RETURNED_COST")
    private BigDecimal returnedCost;

    @Column(name = "CUSTOMER_QTY")
    private BigDecimal customerQty;

    @Column(name = "DAMAGE_QTY")
    private BigDecimal damageQty;

    @Column(name = "MNT_QTY")
    private BigDecimal mntQty;

    @Column(name = "FUND_QTY")
    private BigDecimal fundQty;
}
