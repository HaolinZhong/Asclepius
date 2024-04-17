'use client'

import {createContext, ReactNode, useContext, useEffect, useState} from "react";
import {ChatMessage, ChatMessageRoleEnum} from "@/app/lib/entity/chat-message";
import streamingMessage from "@/app/lib/api/chat-api";

export interface ContextProps {
    messages: ChatMessage[]
    addMessage: (content: string) => Promise<void>
    isStreaming
}

export const ChatContext = createContext<Partial<ContextProps>>({});

export function ChatContextProvider({ children }: { children: ReactNode }) {
    const [messages, setMessages] = useState<ChatMessage[]>([]);
    const [isStreaming, setIsStreaming] = useState<boolean>(false);

    const updateResponseMessage = (newContent: string) => {
        setMessages(currentMessages => {
            const newMessages = [...currentMessages];
            if (newMessages.length > 0) {
                newMessages[length - 1].content = newContent;
            }
            return newMessages;
        });
    }

    useEffect(() => {
        const initializeChat = () => {
            const welcomeMessage: ChatMessage = {
                role: ChatMessageRoleEnum.Assistant,
                content: 'Hi, How can I help you today?',
            }
            setMessages([welcomeMessage])
        }

        // When no messages are present, we initialize the chat the system message and the welcome message
        // We hide the system message from the user in the UI
        if (!messages?.length) {
            initializeChat()
        }
    }, [messages?.length, setMessages])

    const addMessage = async (content: string) => {
        try {
            const requestMessage: ChatMessage = {
                role: ChatMessageRoleEnum.User,
                author: 'user',
                content: content
            };
            const responseMessage: ChatMessage = {
                role: ChatMessageRoleEnum.Assistant,
                author: 'assistant',
                content: ''
            };
            setMessages([...messages, requestMessage, responseMessage]);
            setIsStreaming(true);
            await streamingMessage(requestMessage, updateResponseMessage);
        } catch (error) {
            // addToToast
        } finally {
            setIsStreaming(false);
        }
    }

    return (
        <ChatContext.Provider value={{messages, addMessage, isStreaming}}>
            {children}
        </ChatContext.Provider>
    )
}

export const useMessage: Function = () => {
    return useContext(ChatContext) as ContextProps;
}