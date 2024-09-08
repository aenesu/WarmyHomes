import { Akshar } from "next/font/google";
import { Advent_Pro } from "next/font/google";
import "@/app/global.css";
import "@/styles/styles.scss";
import Header from "@/components/common/header/header";
import Footer from "@/components/common/footer/footer";
import { metadata } from "./metadata";

const akshar = Akshar({ subsets: ["latin"], weight: ["300", "400", "500", "600", "700"] });
export { akshar };

const adventPro = Advent_Pro({ subsets: ["latin"], weight: ["400", "700"] });
export { adventPro };




export default function RootLayout({ children }) {
    return (
        <>      <Head>
        <title>{metadata.title.default}</title>
        <meta name="description" content={metadata.description} />
        <meta name="publisher" content={metadata.publisher} />
        {metadata.authors.map((author, index) => (
          <meta key={index} name={`author-${index}`} content={author.name} />
        ))}
        <meta name="application-name" content={metadata.applicationName} />
        <meta name="generator" content={metadata.generator} />
        <link rel="icon" href={metadata.icons.icon} />
        <meta name="robots" content={metadata.robots} />
        <meta property="og:title" content={metadata.openGraph.title} />
        <meta property="og:description" content={metadata.openGraph.description} />
        <meta property="og:image" content={metadata.openGraph.images[0]} />
      </Head>
        <html lang="en">
            <body
                className={`${akshar.className}`}
                style={{ height: "100% !important" }}
            >
                <Header />

                <div>
                    {children}
                </div>


                <Footer />
            </body>
        </html>
        </>
    )
}
