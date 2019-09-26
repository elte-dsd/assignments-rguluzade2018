# -*- coding: utf-8 -*-
"""
Created on Thu Sep 26 23:46:53 2019

@author: RAMIL
"""


import numpy as np
from bitarray import bitarray
import random


# ch,ch2: hashing coefficients
# val: value to be hashed
# prime: size of hash table


def my_hash(ch, ch2, val, prime): 
    return (a * val + b) % prime


def update_bloom(position, bitarr): bitarr[position] = 1


# find next prime number
def find_next_prime(n): 
    return find_prime_in_range(n, 2 * n)


def find_prime_in_range(a, b):
    for p in range(a, b):
        for i in range(2, p):
            if p % i == 0:
                break
        else:
            return p
    return None


# test
print(find_next_prime(19 + 1))


# example list of spam emails
email_list = ["spam@gmail.com", "spam2@gmail.com",
              "spam3@gmail.com", "aigdirect@rixobalkangrill.com",
              "checkwebsite@gmail.com", "ramil2018@ada.edu.az"]

email_unicode_sum_list = [sum([ord(char) for char in email]) for email in email_list]

hashtable_size = find_next_prime(int(round(len(email_unicode_sum_list) * 1.25)))

# initialising bloom filter
bloom_filter = bitarray(hashtable_size)
# set all bits to 0
bloom_filter[:] = False
print("Bloom Filter:", bloom_filter)

# choose 2 random numbers up to maximum unicode value, then set them to be hashing coefficients
a = random.randint(1, max(email_unicode_sum_list) - 1)
b = random.randint(2, max(email_unicode_sum_list) - 1)

print(a, b)

# choosing next prime number 
hashtable_size = find_next_prime(int(round(len(email_unicode_sum_list) * 1.25)))


# hash emails
hash_list = [my_hash(a, b, unicode_sum, hashtable_size) for unicode_sum in email_unicode_sum_list]


[(email, "->", unicode_sum, "->", hashp) for email, unicode_sum, hashp in
 zip(email_list, email_unicode_sum_list, hash_list)]


[update_bloom(index, bloom_filter) for index in hash_list]

#testing
# check if it has already been seen by bloom filter or not
for email in email_list:
    if bloom_filter[my_hash(a, b, sum([ord(char) for char in email]), hashtable_size)] == True:
        print(email, " Seen")
    else:
        print(email, "Not Seen")