import { Akshar } from "next/font/google";
import "./globals.css";
import "@/styles/styles.scss";
import Header from "@/components/common/header/header";
import Footer from "@/components/common/footer/footer";

const akshar = Akshar({ subsets: ["latin"], weight: ["300", "400", "500", "600", "700"] });

export const metadata = {
    title: {
        default: "Warmy Homes | Find Your Warm Home",
        template: "%s | Warmy Homes"
    },
    description: " Discover and rent the perfect home with ease on Warmy Homes. Whether you're looking to rent out your space or searching for your ideal apartment, Warmy Homes connects you with quality listings and reliable rental solutions. Simplify your rental journey with us today.",
    publisher: "Warmy Homes LLC",
    authors: [
        {
            name: "aenesu",
            url: "https://github.com/aenesu"
        },
        {
            name: "Securiconet",
            url: "https://github.com/Securiconet"
        }, {
            // TODO: add partners here
            name: "aenesu",
            url: "https://github.com/aenesu"
        },
    ],
    creator: "",
    applicationName: "Warmy Homes",
    generator: "Next.js",
    icons: {
        icon: "/assets/images/favicon.ico",
    },
    keyword:
        "Affordable rentals, Luxury apartment rentals, City center flats for rent, Short-term housing rentals, Furnished homes for rent, Pet-friendly rentals, Private landlord listings, Rental properties near me, Cheap studio apartments, Family homes for lease",
    robots: "index, follow",
    openGraph: {

        // TODO: "fix the url after the deployement"

        type: "website",
        locale: "en_US",
        url: "https://www.warmyhomes.com",
        siteName: "Warmy Homes",
        images: [
            {
                url: "/assets/images/logo-white2.png",
                width: 208,
                height: 48,
                alt: "Warmy Homes | Find Your Warm Home",
            },
        ],
        description:
            "Discover and rent the perfect home with ease on Warmy Homes. Whether you're looking to rent out your space or searching for your ideal apartment, Warmy Homes connects you with quality listings and reliable rental solutions. Simplify your rental journey with us today.",
        title: "Warmy Homes | Find Your Warm Home",
    },
};


export default function RootLayout({ children }) {
    return (
        <html lang="en">
            <body
                className={`${akshar.className}`}
                style={{ height: "100% !important" }}
            >
                <Header />

                <div style={{ height: '70vh'}}>
                    {children}
                    hey
                </div>


                <Footer />
            </body>
        </html>
    )
}
