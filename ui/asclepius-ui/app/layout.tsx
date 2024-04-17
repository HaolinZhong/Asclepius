import type {Metadata} from "next";
import "./ui/globals.css";
import {inter} from "@/app/lib/font";

export const metadata: Metadata = {
    title: {
        default: "Asclepius - An AI-powered pre-diagnose system",
        template: "%s | Asclepius"
    },
    description: "An AI-powered pre-diagnose system. Describe your symptom and get recommended physicians",
    icons: {
        icon: '/favicon.ico',
        shortcut: '/favicon.png',
        apple: '/favicon.png'
    },
    metadataBase: process.env.URL
        ? new URL(`https://${process.env.URL}`)
        : undefined,
};

export default function RootLayout({
                                       children,
                                   }: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <html lang="en">
            <body className={`${inter.className} antialiased`}>{children}</body>
        </html>
    );
}
