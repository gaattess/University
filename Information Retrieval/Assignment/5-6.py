# --------------------------------------
# PETRAKI VASILIKI EVANTHIA
# 19390193
# ASKISI 2
# PYTHON 3.10
# --------------------------------------
import nltk as nltk
from nltk.book import *
from collections import *

sentence2 = "The First Circle, which is the Limbo of the Unbaptized and of \
  the Virtuous Heathen--the Great Poets--the Noble Castle--the \
  Sages and Worthies of the ancient world"

bag_of_words2 = Counter(nltk.word_tokenize(sentence2))
bag_of_words4 = Counter(text4[0:50])
bag_of_words7 = Counter(text7[0:50])

print(f"Top 4 common words from my sentence: {bag_of_words2.most_common(4)}\n"
      f"Top 4 common words from text 4: {bag_of_words4.most_common(4)}\n"
      f"Top 4 common words from text 7: {bag_of_words7.most_common(4)}")


# ΟΜΟΙΟΤΗΤΑ ΣΥΝΗΜΙΤΟΝΩΝ
def cosine(text_1, text_2):
    stopwords = nltk.corpus.stopwords.words('english')

    l1 = []
    l2 = []

    text_1 = {w for w in text_1 if w not in stopwords}
    text_2 = {w for w in text_2 if w not in stopwords}

    rvector = text_1.union(text_2)

    for w in rvector:
        if w in text_1:
            l1.append(1)
        else:
            l1.append(0)
        if w in text_2:
            l2.append(1)
        else:
            l2.append(0)
    c = 0

    for i in range(len(rvector)):
        c += l1[i] * l2[i]

    var = c / float((sum(l1) * sum(l2)) ** 0.5)

    return var


print(f"Ομοιότητα συνημίτονων: {cosine(text4[0:50], text7[0:50]):.2%}\n"
      f"Ομοιότητα συνημίτονων σε ολόκληρα τα κείμενα: {cosine(text4, text7):.2%}")
