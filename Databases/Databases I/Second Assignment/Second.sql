--ERWTHMA 1--
SELECT
  ENAME "ENAME",
  CONCAT (SAL, " €") "ΜΙΣΘΟΣ",
  CONCAT (COMM, " €") "ΠΡΟΜΗΘΕΙΑ",
  CONCAT(ROUND((COMM / SAL) * 100, 2), " %") AS "ΠΟΣΟΣΤΟ"
FROM
  EMP;

--ERWTHMA 2--
SELECT
  ENAME "ΕΠΩΝΥΜΟ",
  CONCAT(SAL, " €") "ΜΗΝΙΑΙΕΣ ΑΠΟΔΟΧΕΣ",
  CONCAT(
    FLOOR(DATEDIFF(CURRENT_DATE(), HIRE) / 365),
    " έτη"
  ) "ΠΡΟΫΠΗΡΕΣΙΑ"
FROM
  EMP
WHERE
  FLOOR(DATEDIFF(CURRENT_DATE(), HIRE) / 365) > 20;

--ERWTHMA 3--
SELECT
  ENAME "ΕΠΩΝΥΜΟ",
  JOB "ΘΕΣΗ",
  HIRE "ΠΡΟΣΛΗΨΗ"
FROM
  EMP
WHERE
  day(hire) < 4
  AND (JOB = 'ANALYST' || JOB = 'SALESMAN');

--ERWTHMA 4--
SELECT
  ENAME "ΕΠΩΝΥΜΟ"
FROM
  EMP
WHERE
  (DEPTNO "ΤΜΗΜΑ" = 10);

--ERWTHMA 5--
SELECT
  ENAME "ΕΠΩΝΥΜΟ",
  CONCAT(SAL, " €") "ΜΗΝΙΑΙΕΣ ΑΠΟΔΟΧΕΣ",
  DEPTNO "ΤΜΗΜΑ"
FROM
  EMP
WHERE
  SAL =(
    SELECT
      MAX(SAL)
    FROM
      EMP
    WHERE
      DEPTNO = 10
  )
  OR SAL = (
    SELECT
      MAX(SAL)
    FROM
      EMP
    WHERE
      DEPTNO = 20
  );

--ERWTHMA 6--
SELECT
  ENAME "ΕΠΩΝΥΜΟ",
  CONCAT(SAL, " €") "ΜΗΝΙΑΙΕΣ ΑΠΟΔΟΧΕΣ",
  DEPTNO "ΤΜΗΜΑ"
FROM
  EMP
WHERE(
    DEPTNO = '10'
    AND SAL < (
      SELECT
        MAX(SAL)
      FROM
        EMP
      WHERE
        DEPTNO = '30'
    )
  );
