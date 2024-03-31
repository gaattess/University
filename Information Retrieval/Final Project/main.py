import time
from nltk.corpus import gutenberg
from nltk.tokenize import word_tokenize
import math
from rank_bm25 import BM25L

# Get a list of the fileids from the gutenberg corpus
file_ids = gutenberg.fileids()

# Concatenate the sentences to get the text and make them lowercase
corpus = ' '.join([' '.join(word.lower()) for fileid in file_ids for word in gutenberg.words(fileid)])

# Tokenize the text and create a frequency distribution
N = len(file_ids)


def bm25l(query):
    """Calculating and ranking results with the BM2L algorithm"""
    bm25 = BM25L([gutenberg.words(fileid) for fileid in file_ids])
    return bm25.get_scores(word_tokenize(query))


def calculate_df():
    """
    Calculate DF for all documents.
    """
    frequency = {}  # term -> set of documents in which the term appears

    for fileid in file_ids:
        words = gutenberg.words(fileid)
        for word in words:
            if word in frequency:
                frequency[word].add(fileid)
            else:
                frequency[word] = {fileid}

    df = {}
    for token, appearances in frequency.items():
        df[token] = len(appearances)

    return df


def search_and_rank_tfidf(query):
    """Calculating and ranking results using TF-IDF"""
    query_tokens = word_tokenize(query)
    scores = {}
    results = {}
    for fileid in file_ids:
        words = gutenberg.words(fileid)
        for token in query_tokens:
            term_freq = words.count(token) / len(words)
            df = doc_freq.get(token, 0)
            inv_doc_freq = math.log(N + 1) - math.log(df + 1)

            score = term_freq * inv_doc_freq
            scores[(fileid, token)] = score

    for fileid, token in scores.keys():
        # Try to add the score to the value of results[fileid],
        # if it doesn't exist then catch the exception and initialise it
        try:
            results[fileid] += scores[(fileid, token)]
        except KeyError:
            results[fileid] = scores[(fileid, token)]

    return results


# Perform a search and print the results
query = input("Query Search: ").lower()
while query != ".":
    start = time.process_time()
    doc_freq = calculate_df()

    # list comprehension to create a list that contains both the file id and the score
    bm25_results = [(file_ids[i], score) for i, score in enumerate(bm25l(query))]
    tfidf_results = search_and_rank_tfidf(query)

    # Sorting the results
    sorted_bm25_results = sorted(bm25_results, key=lambda x: x[1], reverse=True)
    sorted_tfidf_results = sorted(tfidf_results.items(), key=lambda x: x[1], reverse=True)

    print("Top 3 matches for BM25L:")
    for i in range(1, 4):
        print(f"{i}. {sorted_bm25_results[i - 1][0]}")

    print("======")

    print("Top 3 matches for TF-IDF:")
    for i in range(1, 4):
        print(f"{i}. {sorted_tfidf_results[i - 1][0]}")

    print(f"Process time {round(time.process_time() - start, 2)}")
    query = input(f"If you do not wish to continue, please press the full-stop character\n"
                  f"Query Search: ").lower()
