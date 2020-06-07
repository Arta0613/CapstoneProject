package com.example.capstoneproject.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.capstoneproject.R;

import static com.example.capstoneproject.domain.SubjectType.KANJI;
import static com.example.capstoneproject.domain.SubjectType.RADICAL;
import static com.example.capstoneproject.domain.SubjectType.VOCABULARY;

public class SubjectHelper {

    private final static String RADICAL_OPEN_TAG = "<radical>";
    private final static String KANJI_OPEN_TAG = "<kanji>";
    private final static String VOCABULARY_OPEN_TAG = "<vocabulary>";

    private final static String RADICAL_CLOSE_TAG = "</radical>";
    private final static String KANJI_CLOSE_TAG = "</kanji>";
    private final static String VOCABULARY_CLOSE_TAG = "</vocabulary>";

    @ColorRes
    public static int getColor(@NonNull final String subjectType) {
        switch (subjectType) {
            case RADICAL:
                return R.color.radical_color;
            case KANJI:
                return R.color.kanji_color;
            case VOCABULARY:
                return R.color.vocabulary_color;
        }

        return -1;
    }

    /**
     * This method takes a regular string that has tags and applies a color to the text based on those tags
     */
    @NonNull
    public static SpannableStringBuilder getColorCodedMnemonic(
            @NonNull final Context context, @NonNull final String mnemonicText
    ) {
        final SpannableStringBuilder mnemonic = new SpannableStringBuilder();
        String mnemonicToReplace = mnemonicText;
        SpannableString coloredText;
        int subjectColor;

        // We want to know the beginning and ending of each open/close tag
        int radicalStartIndex;
        int kanjiStartIndex;
        int vocabularyStartIndex;

        int endOfRadicalStartIndex;
        int endOfKanjiStartIndex;
        int endOfVocabularyStartIndex;

        int startOfRadicalEndIndex;
        int startOfKanjiEndIndex;
        int startOfVocabularyEndIndex;

        int radicalEndIndex;
        int kanjiEndIndex;
        int vocabularyEndIndex;

        int startIndex;
        int endOfStartIndex;
        int startOfEndIndex;
        int endIndex;

        // The mnemonic text contains html tags to denote the japanese language but we don't need these so we get rid of them
        mnemonicToReplace = mnemonicToReplace.replaceAll("<ja>|</ja>", "");

        // Loop through the entire text finding each instance of a tag, applying a color and cutting off what has been processed.
        // At the end of each loop, one set of tags will be deleted (the first tag found) until all tags are processed.
        while (mnemonicToReplace.contains(RADICAL_OPEN_TAG) || mnemonicToReplace.contains(KANJI_OPEN_TAG) || mnemonicToReplace.contains(VOCABULARY_OPEN_TAG)) {

            // Find the beginning of the open tag; we do all the tags since we don't know which comes first
            radicalStartIndex = mnemonicToReplace.indexOf(RADICAL_OPEN_TAG);
            kanjiStartIndex = mnemonicToReplace.indexOf(KANJI_OPEN_TAG);
            vocabularyStartIndex = mnemonicToReplace.indexOf(VOCABULARY_OPEN_TAG);

            // Find the end of the open tag
            endOfRadicalStartIndex = radicalStartIndex + RADICAL_OPEN_TAG.length();
            endOfKanjiStartIndex = kanjiStartIndex + KANJI_OPEN_TAG.length();
            endOfVocabularyStartIndex = vocabularyStartIndex + VOCABULARY_OPEN_TAG.length();

            // Find the beginning of the close tag
            startOfRadicalEndIndex = mnemonicToReplace.indexOf(RADICAL_CLOSE_TAG);
            startOfKanjiEndIndex = mnemonicToReplace.indexOf(KANJI_CLOSE_TAG);
            startOfVocabularyEndIndex = mnemonicToReplace.indexOf(VOCABULARY_CLOSE_TAG);

            // Find the end of the close tag
            radicalEndIndex = startOfRadicalEndIndex + RADICAL_CLOSE_TAG.length();
            kanjiEndIndex = startOfKanjiEndIndex + KANJI_CLOSE_TAG.length();
            vocabularyEndIndex = startOfVocabularyEndIndex + VOCABULARY_CLOSE_TAG.length();

            // Since we are processing these tags by the first one found, we look for the lowest value using Math.min
            // Because Math.min returns a -1 if not found, this will skew the data is -1 is lower than the first tag found (e.g. index 5)
            // We set the indexes for the tag to Integer.MAX_VALUE so that Math.min will work properly
            if (radicalStartIndex == -1 || radicalEndIndex == -1) {
                radicalStartIndex = radicalEndIndex = startOfRadicalEndIndex = endOfRadicalStartIndex = Integer.MAX_VALUE;
            }

            if (kanjiStartIndex == -1 || kanjiEndIndex == -1) {
                kanjiStartIndex = kanjiEndIndex = startOfKanjiEndIndex = endOfKanjiStartIndex = Integer.MAX_VALUE;
            }

            if (vocabularyStartIndex == -1 || vocabularyEndIndex == -1) {
                vocabularyStartIndex = vocabularyEndIndex = startOfVocabularyEndIndex = endOfVocabularyStartIndex = Integer.MAX_VALUE;
            }

            // Find the first tag found and assign the start/end index of the open/close tags
            startIndex = Math.min(Math.min(radicalStartIndex, kanjiStartIndex), vocabularyStartIndex);
            endOfStartIndex = Math.min(Math.min(endOfRadicalStartIndex, endOfKanjiStartIndex), endOfVocabularyStartIndex);
            endIndex = Math.min(Math.min(radicalEndIndex, kanjiEndIndex), vocabularyEndIndex);
            startOfEndIndex = Math.min(Math.min(startOfRadicalEndIndex, startOfKanjiEndIndex), startOfVocabularyEndIndex);

            // Append the beginning of our text until the first tag and append to to our builder
            mnemonic.append(mnemonicToReplace.substring(0, startIndex));

            // Extract the string we want to set a color to (the string within the tag)
            coloredText = new SpannableString(mnemonicToReplace.substring(endOfStartIndex, startOfEndIndex));

            // We are extracting the subject type from within the arrows to determine what background color to apply to the text e.g. <kanji>, we want "kanji" hence (+1 and -1)
            subjectColor = getColor(mnemonicToReplace.substring(startIndex + 1, endOfStartIndex - 1).toLowerCase());

            // Apply a color to the string making the foreground color white and the background color the color of the subject
            coloredText.setSpan(new ForegroundColorSpan(Color.WHITE), 0, coloredText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            coloredText.setSpan(getColorSpan(context, subjectColor), 0, coloredText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            // Append it to our final string
            mnemonic.append(coloredText);

            // Update our working string to remove what we've processed so far (by taking only the text from the end of our processed tag, to the end of the string)
            mnemonicToReplace = mnemonicToReplace.subSequence(endIndex, mnemonicToReplace.length()).toString();
        }

        // Since the loop ends when there are no more tags, we want to append the rest of the string to finish our text
        mnemonic.append(mnemonicToReplace);

        return mnemonic;
    }

    @NonNull
    private static BackgroundColorSpan getColorSpan(
            @NonNull Context context, @ColorRes final int color
    ) {
        return new BackgroundColorSpan(ContextCompat.getColor(context, color));
    }
}