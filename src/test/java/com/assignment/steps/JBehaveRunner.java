package com.assignment.steps;

import com.assignment.SpringbootApplication;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class JBehaveRunner extends JUnitStories {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass().getClassLoader()))
                .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats()
                        .withFormats(Format.HTML, Format.CONSOLE)
                        .withRelativeDirectory("jbehave-report"));
    }


    @Override
    public InjectableStepsFactory stepsFactory() {
        return new SpringStepsFactory(configuration(), applicationContext);
    }


    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().
                findPaths(CodeLocations.codeLocationFromClass(this.getClass()), Arrays.asList("**/*.story"),
                        Arrays.asList(""));
    }
}
