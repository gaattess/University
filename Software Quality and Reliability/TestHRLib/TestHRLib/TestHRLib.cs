using HRLib;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;

namespace TestHRLib
{
    [TestClass]
    public class TestHR
    {
        [TestMethod]
        public void TestValidPassword()
        // bool ValidPassword(string Password)
        {
            // Δημιουργία Test Cases
            object[,] testcases = {
                {1, true, "Mix3dP@ssw0rd3"},
                {2, false, "ShortPW123"},
                {3, true, "Ge0metricP@ss1"},
                {4, false, "!SpecialPW123"},
                {5, false, "NoNumbersHere!"}
            };

            bool failed = false;

            for (int i = 0; i < testcases.GetLength(0); i++)
            {
                try
                {
                    // Δίνουμε ως παράμετρους στην Assert.AreEqual τα στοιχεία του test case
                    Assert.AreEqual((bool)testcases[i, 1], HRLib.Hr.ValidPassword((string)testcases[i, 2]));
                }
                catch (Exception exception)
                {
                    failed = true;
                    Console.WriteLine("Failed Test Case: {0}\n \t Reason: {1} ", (int)testcases[i, 0], exception.Message);
                }
            }

            if (failed) Assert.Fail();
        }

        [TestMethod]
        public void TestCheckPhone()
        // CheckPhone(string Phone, ref int TypePhone, ref string InfoPhone)
        {
            object[,] testcases =
            {
                {1, "2101234567", 0,  "Μητροπολιτική Περιοχή Αθήνας – Πειραιά"},
                {2, "6940123456", 1, "Vodafone"},
                {3, "6950123456", 1, "Vodafone"},
                {4, "6970123456", 1, "Cosmote"},
                {5, "23456", -1, null},
                {6, "2a34567890", -1, null},
                {7, "6923123456", 1, "Unknown"},
                {8, "6900123456", 1, "Nova"}
            };


            bool failed = false;

            for (int i = 0; i < testcases.GetLength(0); i++)
            {
                try
                {
                    // Μεταβλητές μεθόδου ref για να αποθηκευτούν οι τιμές που θα επιστρέψει
                    int validPhoneType = -1;
                    string phoneInfo = "";

                    // Δίνουμε τα στοιχεία του test case 
                    HRLib.Hr.CheckPhone((string)testcases[i, 1], ref validPhoneType, ref phoneInfo);

                    // Δίνουμε τις παραμέτρους που θέλουμε να συγκριθούν ως τιμές
                    Assert.AreEqual((int)testcases[i, 2], validPhoneType);
                    Assert.AreEqual((string)testcases[i, 3], phoneInfo);

                }
                catch (Exception exception)
                {
                    failed = true;
                    Console.WriteLine("Failed Test Case: {0}\n \t Reason: {1} ", (int)testcases[i, 0], exception.Message);
                }
            }

            if (failed) Assert.Fail();
        }

        [TestMethod]
        public void TestValidName()
        {
            // Δημιουργία Test Cases
            object[,] testcases = {
                {1, "Stavros Nastoulis", true},
                {2, "Stavros-Thomas Nastoulis", true},
                {3, "John-Doe", false},
                {4, "JohN", false},
                {5, "george brown", false},
                {6, "First-Name Middle-Name Last-Name", true},
                {7, "St@vros N@stouli", false},
                {8, "Savros1 Nast", false},
                {9, "-Savros Nast", false},
                {10, "Savros Nast-", false}
            };

            bool failed = false;

            for (int i = 0; i < testcases.GetLength(0); i++)
            {
                bool res = HRLib.Hr.ValidName((string)testcases[i, 1]);

                try
                {
                    Assert.AreEqual((bool)testcases[i, 2], res);
                }
                catch (Exception e)
                {
                    //Απέτυχε η περίπτωση 
                    failed = true;
                    //Καταγράφουμε την περίπτωση ελέγχου που 
                    Console.WriteLine("Failed Test Case: {0}. Reason: {1}.", (int)testcases[i, 0], e.Message);
                };
            }

            //Στην περίπτωση που κάποια περίπτωση ελέγχου απέτυχε, πέταξε εξαίρεση.
            if (failed) Assert.Fail();
        }

        // Assert οτι η InfoEmployy επιστρέφει σωστά αποτελέσματα
        [TestMethod]
        public void TestInfoEmployee()
        {
            object[,] testCases = {
                {1, new Employee("Dimitrios Giannakopoulos", "1", "222", new DateTime(1974, 6, 22), new DateTime(2012, 12, 12)), 49, 11}
            };

            bool failed = false;

            for (int i = 0; i < testCases.GetLength(0); i++)
            {
                int age = 0;
                int yearsOfExp = 0;
                HRLib.Hr.InfoEmployee((Employee)testCases[i, 1], ref age, ref yearsOfExp);

                try
                {
                    Assert.AreEqual((int)testCases[i, 2], age);
                    Assert.AreEqual((int)testCases[i, 3], yearsOfExp);
                }
                catch (Exception e)
                {
                    //Απέτυχε η περίπτωση 
                    failed = true;
                    //Καταγράφουμε την περίπτωση ελέγχου που απέτυχε
                    Console.WriteLine("Failed Test Case: {0}. Reason: {1}.", (int)testCases[i, 0], e.Message);
                };
            }

            //Στην περίπτωση που κάποια περίπτωση ελέγχου απέτυχε, πέταξε εξαίρεση.
            if (failed) Assert.Fail();
        }

        // Test την EncryptPassword
        [TestMethod]
        public void TestEncryptPassword()
        {
            object[,] testCases = {
                {1,"password", "ufxx|twi"},
                {2,"a3scr", "f8xhw"},
            };

            bool failed = false;

            for (int i = 0; i < testCases.GetLength(0); i++)
            {
                string encryptedPassword = "";
                HRLib.Hr.ΕncryptPassword((String)testCases[i, 1], ref encryptedPassword);

                try
                {
                    Assert.AreEqual((string)testCases[i, 2], encryptedPassword);
                }
                catch (Exception e)
                {
                    //Απέτυχε η περίπτωση 
                    failed = true;
                    //Καταγράφουμε την περίπτωση ελέγχου που 
                    Console.WriteLine("Failed Test Case: {0}. Reason: {1}.", (int)testCases[i, 0], e.Message);
                };
            }

            //Στην περίπτωση που κάποια περίπτωση ελέγχου απέτυχε, πέταξε εξαίρεση.
            if (failed) Assert.Fail();
        }

        [TestMethod]
        public void TestLiveInAthens()
        {
            Employee[] arrayEmployee1 = {
                new Employee("Maria Papadopoulou", "2105551234", "6978123456", new DateTime(1985, 3, 15), new DateTime(2010, 7, 1)),
                new Employee("Alex Kwnstantinou", "2310333222", "6942001000", new DateTime(1990, 12, 5), new DateTime(2018, 11, 3))
            };

            Employee[] arrayEmployee2 = {
                new Employee("Nikos Stathopoulos", "2106789456", "6934567890", new DateTime(1978, 9, 22), new DateTime(2015, 2, 10))
            };


            object[,] testcases = {
                {1, arrayEmployee1, 1},
                {2, arrayEmployee2, 1}
            };

            bool failed = false;

            for (int i = 0; i < testcases.GetLength(0); i++)
            {
                int res = HRLib.Hr.LiveInAthens((Employee[])testcases[i, 1]);

                try
                {
                    Assert.AreEqual((int)testcases[i, 2], res);
                }
                catch (Exception ex)
                {
                    failed = true;
                    Console.WriteLine("Failed Test Case: {0}. Reason: {1}.", (int)testcases[i, 0], ex.Message);

                }
            }

            if (failed) Assert.Fail();
        }
    }
}
