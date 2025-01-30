package com.example.satellite_launch_risk_assessment;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaunchRepository extends MongoRepository<Launch, ObjectId> {}
