#include <stdio.h>
#include "mpi.h"

/*  Βασιστείτε στο παράδειγμα nsquare.c για να υπολογίσετε το παραγοντικό ενός αριθμού, Ν!=1×2×3...×Ν
    (το Ν εισάγεται από τον χρήστη).
    Θεωρείστε N ακέραιο πολλαπλάσιο των διεργασιών και αλλάξτε την τελική P2P επικοινωνία του nsquare.c
    ώστε τα μερικά γινόμενα να αποστέλλονται στον αρχηγό=0 με συλλογική MPI_Reduce. */

int main(int argc, char **argv)
{
    int my_rank;
    int p, i, res, finres, a1, b1, num, flag;
    int source;
    int target;
    int tag1 = 50;
    int tag2 = 60;
    int endnum;
    int akpol;
    int Npar;
    int root = 0;
    MPI_Status status;

    flag = MPI_Init(&argc, &argv); // Αρχή του περιβάλλοντος MPI

    if (flag != 0) // Ελέγχουμε αν η MPI κάνει ομαλή εκκίνηση
    {
        printf("\nMPI initialization error\n");
        MPI_Abort(MPI_COMM_WORLD, flag);
    }

    MPI_Comm_rank(MPI_COMM_WORLD, &my_rank); // Βρίσκουμε αριθμό διεργασιών
    MPI_Comm_size(MPI_COMM_WORLD, &p);       // Βρίσκουμε την ταυτότητα τις διεργασίας

    if (my_rank == root)
    {
        printf("Dose plithos aritmon:\n");
        do
        {
            scanf("%d", &endnum);

            akpol = endnum % p;

            if (akpol == 0) // Ελέγχουμε εάν το πλήθος που δόθηκε είναι ακέραιο πολλαπλάσιο των διεργασιών
            {
                printf("\nI am plithos: %d\n", endnum);
            }
            else if (akpol != 0)
            {
                printf("\nInput is not an integral multiplier of processes. Please input a correct number\n");
            }
        } while (akpol != 0);

        for (target = 1; target < p; target++) // Στέλνουμε το πλήθος σε κάθε διεργασία
        {
            MPI_Send(&endnum, 1, MPI_INT, target, tag1, MPI_COMM_WORLD);
        }
    }
    else // Η κάθε διεργασία δέχεται το πλήθος
    {
        MPI_Recv(&endnum, 1, MPI_INT, root, tag1, MPI_COMM_WORLD, &status);
    }

    res = 1;
    num = endnum / p;
    a1 = (my_rank * num) + 1;
    b1 = a1 + num - 1;

    for (i = a1; i <= b1; i++)
    {
        res *= i;
    }

    MPI_Reduce(&res, &Npar, 1, MPI_INT, MPI_PROD, root, MPI_COMM_WORLD); // Παίρνει από όλες τις διεργασίες τα γινόμενα και τα πολλαπλασιάζει

    if (my_rank == root) // Εμφανίζει ο αρχηγός το αποτέλεσμα
    {
        printf("\nFinal result N!: %d\n", Npar);
    }

    MPI_Finalize();
}
