package ixalan.movieapp;

import ixalan.movieapp.business.AccessMoviesIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessMoviesIT.class
})
public class IntegrationTests {
}