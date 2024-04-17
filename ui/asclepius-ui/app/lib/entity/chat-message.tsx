export enum ChatMessageRoleEnum {
    User = 'user',
    Assistant = 'assistant'
}

export interface ChatMessage {
    'role': ChatMessageRoleEnum;
    'author'?: String,
    'content': String
}