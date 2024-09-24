#include <stdio.h>
#include <stdlib.h>
#include "mpi.h"

int main(int argc, char **argv)
{

    int rank;     // ID διεργασίας
    int numtasks; // Πόσες διεργασίες τρέχουν
    int root = 0; // Κεντρική διεργασία
    int tag1 = 1, tag2 = 1, tag3 = 10, tag4 = 5, tag5 = 15, tag6 = 9;

    int i;
    int flag;

    int n; // Μήκος του πίνακα

    int sum;       // Άθροισμα των αριθμών που έχουν λάβει οι διεργασίες
    int totalsum;  // Συνολικό άθροισμα διανύσματος
    float avg = 0; // Μέση τιμή

    float varsum, totalvar, var; // Διασπορά

    int lmin, lmax, min, max;
    int secondpart;

    MPI_Status status;

    int array1[200];      // Πίνακας στοιχείων διανύσματος
    int localarray1[200]; // Προσωρινός τοπικός πίνακας

    int akpol, k;

    int numPerCore; // Αριθμοί που θα σταλθούν σε κάθε διεργασία

    /*              BEGIN               */

    flag = MPI_Init(&argc, &argv); // Αρχή του περιβάλλοντος MPI

    if (flag != 0) // Ελέγχουμε αν η MPI κάνει ομαλή εκκίνηση
    {
        printf("\nMPI initialization error\n");
        MPI_Abort(MPI_COMM_WORLD, flag);
    }

    // Βρίσκουμε τον αριθμό των διεργασιών
    MPI_Comm_size(MPI_COMM_WORLD, &numtasks);
    // Βρίσκουμε την ταυτότητα της διεργασίας
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    // Δίνεται το μήκος του πίνακα από τον χρήστη στον κεντρικό επεξεργαστή
    if (rank == root)
    {
        printf("\nGive array length:\n");
        do
        {
            scanf("%d", &n);
            akpol = n % numtasks; // Υπολογίζουμε εάν ο αιρθμός που έδωσε ο χρήστης είναι ακέραιο πολλαπλάσιο του πλήθους των διεργασιών

            if (akpol == 0)
            {
                printf("\nI am n: %d\n", n);
            }
            else if (akpol != 0) // Εάν δεν είναι ακέραιο πολλαπλάσιο, ζητάει νέο νούμερο
            {
                printf("\nLength input is not an integral multiple of processes. Please input a correct number\n");
            }
        } while (akpol != 0);

        // Γεμίζουμε τον πίνακα
        for (i = 0; i < n; i++)
        {
            printf("\nGive X%d\n", i + 1);
            scanf("%d", &array1[i]);
        }

        for (i = 1; i < numtasks; i++) // Στέλνει το μήκος του πίνακα στις άλλες διεργασίες
        {
            MPI_Send(&n, 1, MPI_INT, i, tag1, MPI_COMM_WORLD);
        }

        numPerCore = n / numtasks; // Υπολογίζουμε πόσα νούμερα θα πάρει η κάθε διεργασία
        k = numPerCore;

        for (i = 1; i < numtasks; i++) // Στέλνουμε στις άλλες διεργασίες τον πίνακα
        {
            MPI_Send(&array1[k], numPerCore, MPI_INT, i, tag2, MPI_COMM_WORLD);
            k += numPerCore;
        }

        for (k = 0; k < numtasks; k++) // Αποθηκεύουμε τα στοιχεία προσωρινά σε έναν πίνακα
        {
            localarray1[k] = array1[k];
        }
    }
    else // Οι υπόλοιπες διεργασίες
    {
        MPI_Recv(&n, 1, MPI_INT, root, tag1, MPI_COMM_WORLD, &status); // Δέχονται οι διεργασίες το μέγεθος

        numPerCore = n / numtasks;
        k = numPerCore;

        MPI_Recv(&localarray1[0], numPerCore, MPI_INT, root, tag2, MPI_COMM_WORLD, &status); // Δέχεται η κάθε διεργασία τα στοιχεία που της αντιστοιχούν
    }

    /*              ΕΡΩΤΗΜΑ Α
                ΥΠΟΛΟΓΙΣΜΟΣ ΜΕΣΗΣ ΤΙΜΗΣ             */
    sum = 0;
    for (i = 0; i < numPerCore; i++) // Υπολογίζουμε το σύνολο στις διεργασίες εκτός της κεντρικής
    {
        sum = sum + (localarray1[i]);
    }

    if (rank != root) // Στέλνουν το σύνολο στην κεντρική διεργασία
    {
        MPI_Send(&sum, 1, MPI_INT, root, tag3, MPI_COMM_WORLD);
    }
    else
    {
        totalsum = sum;
        printf("\nProcess %d result = %d\n", rank, sum);
        for (i = 1; i < numtasks; i++) // Εκτελούμε την επανάληψη για τον αριθμό των διεργασιών
        {
            MPI_Recv(&sum, 1, MPI_INT, i, tag3, MPI_COMM_WORLD, &status); // Δέχεται τα σύνολα των άλλων διεργασιών
            totalsum = totalsum + sum;                                    // Προσθέτει και της κεντρικής διεργασίας
            printf("\nProcess %d result = %d\n", i, sum);
        }
        printf("\nTotal sum: %d\n", totalsum);
        avg = (float)totalsum / n; // Υπολογίζει την μέση τιμή των στοιχείων του διανύσματος
        printf("\nAverage: %.2f\n", avg);
    }

    /*              ΕΡΩΤΗΜΑ Β
                ΥΠΟΛΟΓΙΣΜΟΣ ΔΙΑΣΠΟΡΑΣ               */

    if (rank == root)
    {
        for (i = 1; i < numtasks; i++)
        {
            MPI_Send(&avg, 1, MPI_FLOAT, i, tag4, MPI_COMM_WORLD);
        }
    }
    else
    {
        MPI_Recv(&avg, 1, MPI_FLOAT, root, tag4, MPI_COMM_WORLD, &status);
    }

    varsum = 0;
    for (i = 0; i < numPerCore; i++) // Υπολογισμός συνόλου (Xi - m)^2
    {
        varsum = varsum + ((localarray1[i] - avg) * (localarray1[i] - avg));
    }

    if (rank != root) // Στέλνουν το σύνολο στην κεντρική διεργασία
    {
        MPI_Send(&varsum, 1, MPI_FLOAT, root, tag4, MPI_COMM_WORLD);
    }
    else
    {
        totalvar = varsum;
        printf("\nProcess %d result = %.2f\n", rank, varsum);
        for (i = 1; i < numtasks; i++)
        {
            MPI_Recv(&varsum, 1, MPI_FLOAT, i, tag4, MPI_COMM_WORLD, &status); // Δέχεται τα σύνολα των άλλων διεργασιών
            totalvar = totalvar + varsum;                                      // Προσθέτει της κεντρικής διεργασίας
            printf("\nProcess %d result = %.2f\n", i, varsum);
        }
        printf("\nTotal varsum: %.2f\n", totalvar);
        var = (float)totalvar / n; // Υπολογίζει την διασπορά των στοιχείων του διανύσματος
        printf("\nDiaspora: %.2f\n", var);
    }

    /*              ΕΡΩΤΗΜΑ Γ
                    ΔΙΑΝΥΣΜΑ Δ              */

    lmin = localarray1[0]; // Ορίζουμε το 1ο στοιχείο του πίνακα ως το ελάχιστο
    lmax = localarray1[0]; // Ορίζουμε το 1ο στοιχείο του πίνακα ως το μέγιστο

    for (i = 0; i < numPerCore; i++)
    {
        if (localarray1[i] < lmin) // Βρίσκουμε ποιο είναι το ελάχιστο στοιχείο ανάμεσα στις άλλες διεργασίες
        {
            lmin = localarray1[i];
        }

        if (localarray1[i] > lmax) // Βρίσκουμε ποιο είναι το μέγιστο στοιχείο ανάμεσα στις άλλες διεργασίες
        {
            lmax = localarray1[i];
        }
    }

    if (rank != root) // Στέλνουν το μέγιστο και το ελάχιστό τους στην κεντρική διεργασία
    {
        MPI_Send(&lmin, 1, MPI_INT, root, tag5, MPI_COMM_WORLD);
        MPI_Send(&lmax, 1, MPI_INT, root, tag5, MPI_COMM_WORLD);
    }
    else
    {
        min = localarray1[0]; // Ορίζουμε το 1ο στοιχείο του πίνακα ως το ελάχιστο
        max = localarray1[0]; // Ορίζουμε το 1ο στοιχείο του πίνακα ως το μέγιστο

        for (i = 1; i < numtasks; i++)
        {
            MPI_Recv(&lmin, 1, MPI_INT, i, tag5, MPI_COMM_WORLD, &status);
            MPI_Recv(&lmax, 1, MPI_INT, i, tag5, MPI_COMM_WORLD, &status);

            if (lmin < min) // Βρίσκουμε ποιο είναι το ελάχιστο στοιχείο συγκρίνοντας αυτό όλων των διεργασιών
            {
                min = lmin;
            }

            if (lmax > max) // Βρίσκουμε ποιο είναι το μέγιστο στοιχείο συγκρίνοντας αυτό όλων των διεργασιών
            {
                max = lmax;
            }
        }
        printf("\nMin: %d\n", min);
        printf("\nMax: %d\n", max);
        secondpart = (max - min);      // Υπολογίζουμε το (Xmax - Xmin)
        for (i = 1; i < numtasks; i++) // Το στέλνουμε στις υπόλοιπες διεργασίες
        {
            MPI_Send(&secondpart, 1, MPI_INT, i, tag6, MPI_COMM_WORLD);
        }
    }

    MPI_Finalize();
}
