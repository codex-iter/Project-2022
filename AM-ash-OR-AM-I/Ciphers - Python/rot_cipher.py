"""
ROT Cipher a.k.a. Caeser Cipher.
Shifts all of alphabets by n-places/shifts
"""

class ROT:
    def __init__(self, shift=1) -> None:
        self.shift = shift
        
    def decrypt(self, encrypted_text: str):
        cipher_text = ""
        for x in encrypted_text:
            starting_alphabet = 65 if x.isupper() else 97
            position = ord(x) - starting_alphabet
            cipher = (
                chr(starting_alphabet + (position - self.shift) % 26)
                if x.isalpha()
                else x
            )
            cipher_text += cipher
        return cipher_text

    def encrypt(self, plain_text):
        cipher_text = ""
        for x in plain_text:
            starting_alphabet = 65 if x.isupper() else 97
            position = ord(x) - starting_alphabet
            cipher = (
                chr(starting_alphabet + (position + self.shift) % 26)
                if x.isalpha()
                else x
            )
            cipher_text += cipher
        return cipher_text


n = input(
    """Enter your choice:
1. Cipher
2. Decipher/Plain Text
"""
)

shift = int(input("Enter no. of shifts: "))
caesar_cipher = ROT(shift)
text = input("Text: ")

if n == "2":
    plain_text = caesar_cipher.decrypt(text)
    print(f"Deciphered text = {plain_text}")
else:
    cipher_text = caesar_cipher.encrypt(text)
    print(f"Cipher text = {cipher_text}")
