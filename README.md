# General
In general, AbstractDecisionMaker.run() gives the core process, and the extending classes implements some details,
such as how and when to execute the "run()", using a simple algorithm or an AI-powered service, etc.

# Requirements
1. There shall at least one concrete class extending AbstractDecisionMaker (in this example, BatchDecisionMaker)
2. AbstractDecisionMaker coordinates the read/writes to the data store through a Service (ISentenceService)
3. ISentenceService can access a DB (SentenceService) or a Sentence generator (FakeSentenceService)
4. The developer using any class that extends AbstractDecisionMaker shall be free to decide which decision algorithm to use and its implementation

# Problems
1. When executing Unit Tests on BatchDecisionMakerTest, get the error "Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'demoApplication': Unsatisfied dependency expressed through field 'decisionAlgorithm'; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.example.demo.component.IDecisionAlgorithm' available: expected single matching bean but found 2: simpleDecision,createDecisionAlgorithm"
2. When running Unit Tests, all properties with @Value, inside component or services, are never read: why?
3. I'm pretty sure this isn't the right design. Ideally, I would like to leave as much freedom as possible to use AbstractDecisionMaker. What is missing? And what should be changed?
