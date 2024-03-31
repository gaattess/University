#--------------------------------------
# PETRAKI VASILIKI EVANTHIA
# 19390193
# ASKISI 2
# PYTHON 3.10
#--------------------------------------
import string
import nltk as nltk
from nltk.book import *
import numpy as numpy
import pandas as pd


def numVec(token_sequence):
    """Συνάρτηση πίνακα συμπτώσεων"""
    vocab = sorted(set(token_sequence))

    ', '.join(vocab)
    num_tokens = len(token_sequence)
    vocab_size = len(vocab)
    onehot_vectors = numpy.zeros((num_tokens, vocab_size), int)

    for i, word in enumerate(token_sequence):
        onehot_vectors[i, vocab.index(word)] = 1
    ' '.join(vocab)
    c1 = pd.DataFrame(onehot_vectors, columns=vocab)
    return c1


# ΠΡΟΤΑΣΗ 1
sentence1 = "Thomas Jefferson began building Monticello at the age of 26."
print(f"Tokenization from book: {sentence1.split()}\nTokenization with nltk: {nltk.word_tokenize(sentence1)}")

print(f"Vector with book tokenization:\n{numVec(sentence1.split())}\n"
      f"Vector with nltk tokenization:\n{numVec(nltk.word_tokenize(sentence1))}")

# ΠΡΟΤΑΣΗ 2
sentence2 = "That is not dead which can eternal lie, And with strange eons even death may die."
print(f"Tokenization from book: {sentence2.split()}\nTokenization with nltk: {nltk.word_tokenize(sentence2)}")

print(f"Vector with book tokenization:\n{numVec(sentence2.split())}\n"
      f"Vector with nltk tokenization:\n{numVec(nltk.word_tokenize(sentence2))}")

# Νέες προτάσεις
sentence3 = "But do cats eat bats, I wonder?"
sentence4 = "And here Alice began to get rather sleepy, “Do cats eat bats? Do cats eat bats?”"
sentence5 = "and sometimes, “Do bats eat cats?”"

corpus1 = {'sent3': dict((tok, 1) for tok in nltk.word_tokenize(sentence3)),
           'sent4': dict((tok, 1) for tok in nltk.word_tokenize(sentence4)),
           'sent5': dict((tok, 1) for tok in nltk.word_tokenize(sentence5))}

df1 = pd.DataFrame.from_records(corpus1).fillna(0).astype(int).T
print(f"Similar words between the three new sentences:\n"
      f"{df1}")

# Βιβλία 4 και 7 του nltk

stringText4 = " ".join(text4[0:50])
stringText7 = " ".join(text7[0:50])

corpus2 = {
    'text4': dict((tok, 1) for tok in nltk.word_tokenize(stringText4)),
    'text7': dict((tok, 1) for tok in nltk.word_tokenize(stringText7))}

df2 = pd.DataFrame.from_records(corpus2).fillna(0).astype(int).T
print(f"Similar words between the 2 books:\n"
      f"{df2}")


# ΕΡΩΤΗΜΑ 4
def cleaner(tokens, language):
    """ Η συνάρτηση "καθαρίζει" ένα κείμενο από προθήματα και σημεία στίξης """

    cleaned_tokens = []
    stopwords = nltk.corpus.stopwords.words(language)

    for token in tokens:
        token = token.lower()  # Κάνουμε τις λέξεις πεζές, καθώς δε λειτουργεί αλλιώς
        if (token not in string.punctuation) and (token not in stopwords):
            cleaned_tokens.append(token)

    return cleaned_tokens


def postList(text):
    """ Η συνάρτηση εμφανίζει σε μορφή posting list, τις πρώτες 3 λέξεις ενός κειμένου"""
    dict_text = {}
    dict_max3 = {}

    for pos, term in enumerate(nltk.word_tokenize(text)):
        if term in dict_text:
            dict_text[term][0] = dict_text[term][0] + 1
            dict_text[term][1].append(pos)
        else:
            dict_text[term] = []
            dict_text[term].append(1)
            dict_text[term].append({})
            dict_text[term][1] = [pos]

    for i in range(3):
        max0 = max(dict_text, key=dict_text.get)
        dict_max3[max0] = dict_text[max0]
        dict_text.pop(max0)

    return dict_max3


stringText4 = " ".join(cleaner(text4[0:50], 'english'))
stringText7 = " ".join(cleaner(text7[0:50], 'english'))

print(f"Top 3 most common words in text 4: {postList(stringText4)}\n"
      f"Top 3 most common words in text 7: {postList(stringText7)}")
