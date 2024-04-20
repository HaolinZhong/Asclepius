import SideNav from "@/app/ui/dashboard/sidenav";
import type {Metadata} from "next";
import "../ui/globals.css";
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

export default function Layout({children}: { children: React.ReactNode }) {
    return (
        <html lang="en">
            <body className={`${inter.className} antialiased bg-gray-50`}>
                <div className="flex h-screen flex-col md:flex-row md:overflow-hidden">
                    <div className="w-full flex-none md:w-64">
                        <SideNav/>
                    </div>
                    <div className="flex-grow p-6 md:overflow-y-auto md:p-12">{children}</div>
                </div>
            </body>
        </html>
    );
}