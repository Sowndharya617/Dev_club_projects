import json
from difflib import get_close_matches

def load_knowledge_base(file_path: str) -> dict:
    with open(file_path, 'r') as file:
        return json.load(file)

def save_knowledge_base(file_path: str, data: dict):
    with open(file_path, 'w') as file:
        json.dump(data, file, indent=2)

def find_best_match(user_question: str, questions: list) -> str:
    return get_close_matches(user_question.lower(), [q.lower() for q in questions], n=1, cutoff=0.6)

def get_answer_for_question(question: str, knowledge_base: dict) -> str:
    for q in knowledge_base["questions"]:
        if q["question"].lower() == question.lower():
            return q["answer"]

def chat_bot():
    knowledge_base = load_knowledge_base('knowledge_base.json')

    while True:
        user_input = input('You: ')
        if user_input.lower() == 'quit':
            break

        best_match = find_best_match(user_input, [q["question"] for q in knowledge_base["questions"]])

        if best_match:
            answer = get_answer_for_question(best_match[0], knowledge_base)
            print(f'Bot: {answer}')
        else:
            new_answer = input('Bot: I don\'t know the answer. Can you teach me? Type answer or "skip": ')
            if new_answer.lower() != 'skip':
                knowledge_base["questions"].append({"question": user_input, "answer": new_answer})
                save_knowledge_base('knowledge_base.json', knowledge_base)
                print('Bot: Thank you! I learned a new response!')

if __name__ == '__main__':
    chat_bot()
