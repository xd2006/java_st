package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Alex on 19.07.2016.
 */
public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("37d8859b4a6c01c14277cec634afcd2a195e5755");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("xd2006", "java_st")).commits();
        for (RepoCommit commit:commits.iterate(new ImmutableMap.Builder<String,String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }

}
}
