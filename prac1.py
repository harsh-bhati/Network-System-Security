def get_key():
    while True:
        try:
            key = int(input("Enter the Key (0-25): "))
            if 0 <= key <= 25:
                return key
            else:
                print("Invalid input! Please enter a number between 0 and 25.")
        except ValueError:
            print("Invalid input! Please enter a valid integer.")

def caesar_cipher(text, key, mode='encrypt'):
    result = ""
    shift = key if mode == 'encrypt' else -key  # Shift right for encryption, left for decryption
    
    for char in text:
        if char.isalpha():
            base = ord('A') if char.isupper() else ord('a')
            result += chr((ord(char) - base + shift) % 26 + base)
        else:
            result += char  # Keep non-alphabetic characters unchanged. for eg. : abc_def
    
    return result

def main():

    while True:
        print("************************Ceaser Cypher*****************************")
        
        mode = input("Enter mode (encrypt/decrypt): ").strip().lower()
        match mode:
            case 'encrypt' | 'decrypt':
                txt = input("Enter text: ")
                key = get_key()
                print("Encrypted text:", caesar_cipher(txt, key, mode))
            case _:
                print("Invalid mode. Please enter 'encrypt' or 'decrypt'.")
            
#ceaser-cypher program.
if __name__ == "__main__":
    main()
