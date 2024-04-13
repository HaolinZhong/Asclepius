import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: {
    default: "Asclepius - An AI-powered pre-diagnose system",
    template: "%s | Asclepius"
  },
  description: "An AI-powered pre-diagnose system. Describe your symptom and get recommended physicians",
  icons: {
    icon: '/favicon.ico',
    shortcut: '/favicon.png'
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
      <body className={inter.className}>{children}</body>
    </html>
  );
}
