public class Test {
    public static void main(String[] args) {
        int size = Trie.getDicSize();
        SegmentationImpl1 seg1 = new SegmentationImpl1();
        SegmentationImpl2 seg2 = new SegmentationImpl2();

        String test1 = "thefollowingtaskwillalsobeaplusifsolvedyoucanperformspecialprocessingoftheinstructionsfieldoftheordersupposesomewordsoftheinstructionsfieldwereunintentionallygluedtogetherlikethisasortoftypingerrorwhensomewhitespacesomittedsecurityonthewebisbasedonavarietyofmechanismsincludinganunderlyingconceptoftrustknown";
        String test2 = "catdogpetrealgoodtastyveryoldcarmagicbigballtoy";
        String test3 = "asthesameoriginpolicygivenaninstructionsstringlikethisandadictionaryofvalidenglishwordsyoushouldreplaceeachcontinuouschainwhichisnotfoundinthedictionarywithaspaceseparatedsequenceofdictionarywordsonavarietyofmechanismscanbereplacedwithonavarietyofmechanismsifachaincannotbebrokenintowordsitisleftintactifachaincanbebrokeninmultipledifferentwaysasequencecontainingminimalnumberofwordsischosenforexamplethechainanunderlyingconceptcanbesplitupasanunderlyingconceptorasanunderlyingconceptwhichispreferred";

        int len = test2.length();

        System.out.println("Complexity: O(dictionary.size * str.length);");
        System.out.println("O(n * m) = " + len * size);
        System.out.println("dic.size = " + size + "; str.length = " + len);
        System.out.println(seg1.segmentString(test2));
//        System.out.println(seg2.segmentString(test2));


    }
}
