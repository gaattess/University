using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;

namespace HRLib
{
    public struct Employee
    {
        public string Name;
        public string HomePhone;
        public string MobilePhone;
        public DateTime Birthday;
        public DateTime HiringDate;

        public Employee(string name, string homePhone, string mobilePhone, DateTime birthday, DateTime hiringDate)
        {
            Name = name;
            HomePhone = homePhone;
            MobilePhone = mobilePhone;
            Birthday = birthday;
            HiringDate = hiringDate;
        }
    }

    public class Hr
    {
        public static bool ValidPassword(string Password)
        {
            bool has_lower = false;
            bool has_symbol = false;

            if (Password.Length <= 12)
            {
                // Πρέπει ο κωδικός να είναι τουλάχιστον 12 χαρακτήρες
                return false;
            }

            foreach (char c in Password)
            {
                if (char.IsLetter(c))
                {
                    if (char.IsLower(c)) has_lower = true;

                    if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) return false;  // Μόνο Λατινικοί χαρακτήρες
                }

                if (!char.IsLetterOrDigit(c)) has_symbol = true;
            }
            if (!has_lower || !has_symbol) return false; // Πρέπει να έχει τουλάχιστον ένα πεζό και τουλάχιστον ένα σύμβολο 

            if (!char.IsUpper(Password, 0) || !char.IsDigit(Password, Password.Length - 1))
            {
                // Πρέπει να ξεκινάει με κεφαλαίο και να τελειώνει με αριθμό
                return false;
            }

            return true;
        }


        public static void CheckPhone(string phone, ref int phoneType, ref string phoneInfo)
        {

            if (phone.Length != 10 || !phone.All(char.IsDigit))
            {
                // Για να είναι τηλέφωνο πρέπει να έχει 10 ψηφία
                phoneType = -1;
                phoneInfo = null;
            }
            else  if (IsLandline(phone))
            {
                // Έλεγχος σταθερού τηλεφώνου
                phoneType = 0;
                phoneInfo = GetLandlineZone(phone);
            }
            else if (IsMobile(phone))
            {
                // Έλεγχος κινητού τηλεφώνου
                phoneType = 1;
                phoneInfo = GetMobileCompany(phone);
            }
            else
            {
                phoneType = -1;
                phoneInfo = null;
            }
        }

        public static bool IsLandline(string phone)
        {
            // Θα πρέπει το πρώτο ψηφίο να είναι 2 και το επόμενο από 1 έως και 8
            return phone[0] == '2' && phone[1] >= '1' && phone[1] <= '8';
        }

        public static string GetLandlineZone(string phone)
        {
            // Οι ζώνες χωρίζονται ανάλογα με το 2ο ψηφίο
            char secondChar = phone[1];
            switch (secondChar)
            {
                case '1':
                    return "Μητροπολιτική Περιοχή Αθήνας – Πειραιά";

                case '2':
                    return "Ανατολική Στερεά Ελλάδα, Αττική, Νησιά Αιγαίου";

                case '3':
                    return "Κεντρική Μακεδονία";

                case '4':
                    return "Θεσσαλία, Δυτική Μακεδονία";

                case '5':
                    return "Θράκη, Ανατολική Μακεδονία";

                case '6':
                    return "Ήπειρος, Δυτική Στερεά Ελλάδα, Δυτική Πελοπόννησος, Ιόνια Νησιά";

                case '7':
                    return "Ανατολική Πελοπόννησος, Κύθηρα";

                case '8':
                    return "Κρήτη";

                default:
                    return "Άγνωστο";
            }
        }

        public static bool IsMobile(string phone)
        {
            return phone.StartsWith("69") && (phone[2] >= '0' && phone[2] <= '9');
        }

        public static string GetMobileCompany(string phone)
        {
            // Επιστρέφει τον πάροχο του κινητού
            char thirdChar = phone[2];
            if (thirdChar == '0' || thirdChar == '3' || thirdChar == '9')
            {
                return "Nova";
            }
            else if (thirdChar == '4' || thirdChar == '5')
            {
                return "Vodafone";
            }
            else if (thirdChar == '7' || thirdChar == '8')
            {
                return "Cosmote";
            }
            else
            {
                return "Unknown";
            }
        }

        public static int LiveInAthens(Employee[] Empls)
        {
            int count = 0;

            foreach (Employee Empl in Empls)
            {
                int PhoneType = -1;
                string phoneInfo = "";
                CheckPhone(Empl.HomePhone, ref PhoneType, ref phoneInfo);

                if (PhoneType == 0 && phoneInfo.Contains("Μητροπολιτική Περιοχή Αθήνας – Πειραιά"))
                {
                    // Ελέγχουμε εάν ο υπάλληλος ζει στην Αθήνα
                    count++;
                }
            }
            return count;
        }

        // Για να είναι ένα όνομα valid πρέπει να ισχύουν όλα τα παρακάτω:
        //   - Να αποτελείτε μόνο από χαρακτήρες, κενά και παύλες
        //   - Να υπάρχει τουλάχιστον ένα κενό
        //   - Να μην υπάρχουν σύμβολα (πέρα από παύλες) και νούμερα
        public static bool ValidName(string name)
        {
            string[] parts = name.Split(' ');

            // Όνομα + Επώνυμο
            if (parts.Length < 2)
            {
                return false;
            }

            // Πρέπει να ξεκινάει με κεφαλαίο
            if (char.IsLower(name[0]))
            {
                return false;
            }

            for (int i = 0; i < name.Length; i++)
            {
                char c = name[i];

                // Χαρακτήρας που δεν είναι γράμμα, ` `, ή `-`
                if (!char.IsLetter(c) && c != '-' && c != ' ')
                {
                    return false;
                }

                // Το `-` δεν μπορεί να είναι στην άκρη του μηνύματος
                if (c == '-' && (i == 0 || i + 1 == name.Length))
                {
                    return false;
                }
            }

            return true;
        }

        public static void InfoEmployee(Employee Empl, ref int Age, ref int YearsOfExperience)
        {
            Age = (DateTime.Now - Empl.Birthday).Days / 365;
            YearsOfExperience = (DateTime.Now - Empl.HiringDate).Days / 365;
        }

        public static void ΕncryptPassword(string Password, ref string ΕncryptedPW) {
            ΕncryptedPW = "";
            foreach (char c in Password)
            {
                int result = c + 5;
                if (result > 127) {
                    result -= 128;
                }

                ΕncryptedPW += ((char)result).ToString();
            }
        }
    }
}
