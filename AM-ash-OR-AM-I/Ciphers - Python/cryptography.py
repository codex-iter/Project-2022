"""
Below is my custom implementation for cipher/deciphering messages
"""
import string

all_characters = string.printable[:-6]


class Encryption:
    def encrypt(self, plain_text):
        encrypted_str = ""
        factor = len(plain_text)  # Length based key
        for letter in plain_text:
            for char in all_characters:
                if letter == char:
                    ordinal = ord(letter)
                    encrypted_str += str(ordinal / factor) + "?"
        return encrypted_str

    def decrypt(self, encrypted_str):
        en_key = ""
        result = ""
        length = 0
        for i in encrypted_str:
            if i == "?":
                length += 1
        for i in encrypted_str:
            if i == "?":
                ordinal = round(float(en_key) * length)
                char = chr(ordinal)
                result += char
                en_key = ""
            else:
                en_key += i  # if not '?'
        return result


encryption = Encryption()

ch = int(input(
    """Enter your choice:
1. Encrypt
2. Decrypt
"""
))
if ch == 1:
    text = input("Enter the text to encrypt: ")
    encrypted_text = encryption.encrypt(text)
    print("Encrypted text =", encrypted_text)
else:
    encrypted_text = input("Enter text to decrypt: ")
    plain_text = encryption.decrypt(encrypted_text)
    print(plain_text)
